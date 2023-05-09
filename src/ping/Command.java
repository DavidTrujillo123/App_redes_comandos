/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JTextArea;

/**
 *
 * @author david
 */
public class Command implements Runnable {

    private String ipAddress;
    private int option;
    private boolean band = true;
    private javax.swing.JTextArea result;

    public Command(JTextArea result) {
        this.result = result;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public void setBand(boolean band) {
        this.band = band;
    }

    public void Command(String[] op) {
        try {
            Process process = Runtime.getRuntime().exec(op);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            String beforeLine = "";
            while ( band == true && !Thread.currentThread().isInterrupted()) {
                line = reader.readLine();
                if(line == null)
                    break;
                result.setText(beforeLine + line + "\n");
                beforeLine += line + "\n";
            }
            result.setText(result.getText()+"\nCommand finished!");
            reader.close();
            process.destroy();
        } catch (IOException e) {
            result.setText(e.getMessage());
        }
    }

    public String CommandOneOption() {

        return null;
    }
//    Ping

    public void DoPing() {
        String[] op = {"ping", this.ipAddress};
        Command(op);
    }

    public void DoPingN() {
        String[] op = {"ping", "-n", "10", this.ipAddress};
        Command(op);
    }

    public void DoPingT() {
        String[] op = {"ping", "-t", this.ipAddress};
        Command(op);
    }

    public void DoPingL() {
        String[] op = {"ping", this.ipAddress, "-l", "1000"};
        Command(op);
    }

    public void Tracert() {
        String[] op = {"tracert", this.ipAddress};
        Command(op);
    }

    public void TracertD() {
        String[] op = {"tracert", "-d", this.ipAddress};
        Command(op);
    }

    public void TracerH() {
        String[] op = {"tracert", "-h", "10", this.ipAddress};
        Command(op);
    }

    public void Nslookup() {
        String[] op = {"nslookup", this.ipAddress};
        Command(op);
    }
    public void Netsh(){
        String[] op = {"netsh", "interface", "ipv4", "show", "addresses"};
        Command(op);
    }
    public boolean parar = true;
    @Override
    public void run() {
            if (option == 0) {
                DoPing();
            } else if (option == 1) {
                DoPingN();
            } else if (option == 2) {
                DoPingT();
            } else if (option == 3) {
                DoPingL();
            } else if (option == 4) {
                Tracert();
            } else if (option == 5) {
                TracertD();
            } else if (option == 6) {
                TracerH();
            } else if (option == 7) {
                Nslookup();
            }
            else if (option == 8){
                Netsh();
            }
    }
}
