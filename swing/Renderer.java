package swing;

import Exceptions.CzlowiekIsDead;
import Swiat.Swiat;
import Swiat.InstanceManager;
import organizmy.Organizm;
import organizmy.Zwierzeta.Gatunki.Czlowiek;
import organizmy.Zwierzeta.Zwierze;

import java.lang.Math;
import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.event.*;
import java.awt.*;

public class Renderer {
    JFrame frame = new JFrame();
    JPanel buttonPanel;
    InstanceManager i;
    Swiat s;

    private class keyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN ||
                    code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT) {

                s.moveWolrd(code);
                updateWolrd();
            } else if (code == KeyEvent.VK_K) { //umiejetnosc czlowieka
                try {
                    ((Czlowiek) s.getCzlowiek()).activatePower();
                } catch (CzlowiekIsDead ex) {
                    System.out.println("Czlowiek jest martwy");
                    System.exit(0);
                }
            } else if (code == KeyEvent.VK_T) { // save
                i.save();
            } else if (code == KeyEvent.VK_Y) { // load
                i.load();
                updateWolrd();
            } else if (code == KeyEvent.VK_Q)
                System.exit(3);
        }
    }

    private int toInt(String s) {
        int l=0;
        for (int i=0; i<s.length(); i++)
            l+=(s.charAt(i)-48)*Math.pow(10,s.length()-1-i);
        return l;
    }

    public Renderer(Swiat swiat, InstanceManager man) {
        s = swiat;
        i = man;
        buttonPanel = new JPanel(new GridLayout(s.MAP_SIZE, s.MAP_SIZE));
        frame.setPreferredSize(new Dimension(600, 630));
        frame.setFocusable(true);
        frame.setVisible(true);

        for (int i=0; i<s.MAP_SIZE*s.MAP_SIZE; i++) {
            JButton b = new JButton();
            b.setUI(new MetalButtonUI());
            b.setName(i+"");
            b.setBorder(null);
            b.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (b.getBackground() != Color.white)
                        return;

                    JFrame opcje = new JFrame();
                    opcje.setPreferredSize(new Dimension(300, 200));
                    JPanel opt = new JPanel(new GridLayout(5, 2));

                    for (int i=0; i<10; i++) { //generowanie nazwy z indeksu w pentli
                        JButton bb = new JButton(s.classNameFromKey(i));
                        bb.setUI(new MetalButtonUI());
                        bb.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                String name = bb.getText();
                                int pos=toInt(b.getName());
                                int x=pos%s.MAP_SIZE;
                                int y=pos/s.MAP_SIZE;
                                swiat.addOrganism(s.generatorFromString(name, x, y));
                                swiat.moveChildren();
                                updateWolrd();
                                opcje.dispose();
                            }
                        });
                        opt.add(bb);
                    }
                    opcje.add(opt);
                    opcje.setVisible(true);
                    opcje.pack();
                    opcje.setLocationRelativeTo(null);
                    frame.requestFocusInWindow();
                }
            });
            buttonPanel.add(b);
        }
        JLabel label = new JLabel(" k - umiejetnosc, t - save, y - read");
        label.setVisible(true);
        label.setPreferredSize(new Dimension(400, 20));
        frame.setLayout(new BorderLayout());
        frame.add(label, BorderLayout.NORTH);
        frame.add(buttonPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.requestFocusInWindow();
        frame.addKeyListener(new keyListener());
    }

    public void updateWolrd() {
        for (int y=0; y<s.MAP_SIZE; y++) {
            for (int x=0; x<s.MAP_SIZE; x++) {
                Component c = buttonPanel.getComponent(y*s.MAP_SIZE+x);
                Organizm o = s.OrganizmOnPos(x, y);

                if (o == null) {
                    c.setBackground(Color.white);
                    ((JButton)c).setText("");
                }
                else if (o instanceof Czlowiek) {
                    c.setBackground(Color.red);
                }
                else if (o instanceof Zwierze) {
                    c.setBackground(Color.orange);
                    ((JButton)c).setText(o.getClass().getSimpleName().charAt(0)+"");
                }
                else {
                    c.setBackground(Color.green);
                    ((JButton)c).setText(o.getClass().getSimpleName().charAt(0)+"");
                }
            }
        }
        frame.repaint();
    }
}
