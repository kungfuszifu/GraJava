import Swiat.Swiat;
import Swiat.InstanceManager;
import swing.Renderer;

public class Main {

    public static void main(String[] args) {
        Swiat swiat = new Swiat();
        InstanceManager i = new InstanceManager(swiat);
        Renderer r = new Renderer(swiat, i);
        r.updateWolrd();
    }
}
