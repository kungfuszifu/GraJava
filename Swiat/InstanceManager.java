package Swiat;

import organizmy.Organizm;

import Swiat.Swiat;
import swing.Renderer;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Vector;

public class InstanceManager {
    Swiat s;
    Swiat.AccessPrivate access;

    public InstanceManager(Swiat s) {
        this.s = s;
    }

    public void getAccess(Swiat.AccessPrivate a) {
        access = a;
    }

    public void save() {
        JFrame frame = new JFrame();

        String result = (String) JOptionPane.showInputDialog(
                frame,
                "Podaj nazwe zapisu",
                "Save menu",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                ""
        );
        if (result != null && result.length() > 0) {
            System.out.println(result);
        }

        BufferedWriter writer = null;
        try {
            s.giveAccess(this);
            File file = new File(result+".txt");
            writer = new BufferedWriter(new FileWriter(file));
            Vector<Organizm> organizm = access.accessVector();

            for (Organizm o: organizm) {
                if (o.isDead())
                    continue;

                writer.write(o.getClass().getSimpleName()+" "+o.getX()+" "+o.getY());
                if (o != organizm.lastElement())
                    writer.write("\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
        }

        frame.setLocationRelativeTo(null);
    }

    public void load() {
        JFrame frame = new JFrame();

        String result = (String) JOptionPane.showInputDialog(
                frame,
                "Podaj nazwe save-a",
                "Load menu",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                ""
        );
        if (result == null)
            return;

        Scanner reader = null;
        try {
            s.giveAccess(this);
            File file = new File(result+".txt");
            reader = new Scanner(file);
            Vector<Organizm> organizm = access.accessVector();

            s.clear();
            while (reader.hasNextLine()) {
                String className = reader.next();
                int x = reader.nextInt();
                int y = reader.nextInt();
                s.addOrganism(s.generatorFromString(className, x, y));
            }
            s.moveChildren();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(10);
        }
        finally {
            if (reader != null)
                reader.close();
        }

        frame.setLocationRelativeTo(null);
    }
}
