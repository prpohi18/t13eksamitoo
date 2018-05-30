package kysimustik;

import java.awt.*;  
import java.awt.event.*;  
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;  
import javax.swing.event.ChangeListener;
import java.util.Date;

public abstract class Test extends JFrame implements ActionListener {
    
    JLabel l;  
    JRadioButton[] jb = new JRadioButton[5];  
    JButton b1, b2;  
    ButtonGroup bg;  
    int count = 0, current = 0, x = 1, y = 1, now = 0;  
    int m[] = new int[10];
    Date startTime;
    
    public Test(String s) {
        super(s);
//        Slider küsimuste arvuks
//        JSlider kys = new JSlider(0, 10, 5);
//        kys.addChangeListener((ChangeListener) this);
//        kys.setMajorTickSpacing(10);
//        kys.setMinorTickSpacing(1);
//        kys.setPaintTicks(true);
//        kys.setPaintLabels(true);
//        add(kys);
//        b1 = new JButton("Edasi");
//        add(b1);
        
        l = new JLabel();  
        add(l);  
        bg = new ButtonGroup();
        
        for(int i = 0; i < 5; i++) {  
            jb[i] = new JRadioButton();     
            add(jb[i]);  
            bg.add(jb[i]);
        }
        
        b1 = new JButton("Järgmine");  
        b2 = new JButton("Märgista");  
        b1.addActionListener(this);  
        b2.addActionListener(this);  
        add(b1);
        add(b2);
        startTime = new Date();
        set();  
        l.setBounds(30, 40, 450, 20);  
        jb[0].setBounds(50, 80, 100, 20);  
        jb[1].setBounds(50, 110, 100, 20);  
        jb[2].setBounds(50, 140, 100, 20);  
        jb[3].setBounds(50, 170, 100, 20);  
        b1.setBounds(100, 240, 100, 30);  
        b2.setBounds(270, 240, 100, 30);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLayout(null);  
        setLocation(250, 100);  
        setVisible(true);  
        setSize(600, 350);  
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == b1) {
            if (check()) {
		count = count + 1;
                try {
                    writeFile(current, startTime.toString(), true);
                } catch (IOException ex) {
                    Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            try {
                writeFile(current, startTime.toString(), false);
            } catch (IOException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
            current++;
            // Küsimuse väljastamine
            set();
            startTime = new Date();
            if (current == 9) {
                // Viimase küsimuse puhul "Järgmine" nupp mitteaktiivseks
                b1.setEnabled(false);
                b2.setText("Tulemus");
            }
        }
            
        if (e.getActionCommand().equals("Märgista")) {
            JButton bk = new JButton(("Tagasi" + x));
            bk.setBounds(480, 20+30*x, 100, 30);
            add(bk);
            bk.addActionListener(this);
            m[x] = current;
            x++;
            current++;
            set();	
            if (current == 9) {
                b2.setText("Tulemus");
            }
            setVisible(false);
            setVisible(true); 
	}
        
        for (int i=0, j=1; i<x; i++, j++) {
            if (e.getActionCommand().equals(("Tagasi") + j)) {
		if (check()) {
                    count = count+1;
                }
                now = current;
                current = m[j];
                set();
                ((JButton)e.getSource()).setEnabled(false);
                current = now;
            }
        }
            
        if(e.getActionCommand().equals("Tulemus")) {
            if(check()) {
		count=count+1;
            }
            current++;
            JOptionPane.showMessageDialog(this, "Vastasid õigesti " + count + " küsimusele");
            System.exit(0);
	}
    }
    
    private void set() {
	jb[4].setSelected(true);
	if(current==0) {
	    l.setText("1. Milline neist ei ole lihtandmetüüp?");
            jb[0].setText("int");
            jb[1].setText("float");
            jb[2].setText("boolean");
            jb[3].setText("String");	
	}
        
        if(current==1)  
        {  
            l.setText("2. Millise võtmesõnaga saad teha klassist uue isendi?");  
            jb[0].setText("new");
            jb[1].setText("get");
            jb[2].setText("initialize");
            jb[3].setText("create");  
        }  
        if(current==2)  
        {  
            l.setText("3. küsimus?");  
            jb[0].setText("vastus1");
            jb[1].setText("vastus2");
            jb[2].setText("vastus3");
            jb[3].setText("vastus4");  
        }  
        if(current==3)  
        {  
            l.setText("4. küsimus");  
            jb[0].setText("vastus1");
            jb[1].setText("vastus2");
            jb[2].setText("vastus3");
            jb[3].setText("vastus4");  
        }  
        if(current==4)  
        {  
            l.setText("5. küsimus");  
            jb[0].setText("vastus1");
            jb[1].setText("vastus2");
            jb[2].setText("vastus3");
            jb[3].setText("vastus4");  
        }  
        if(current==5)  
        {  
            l.setText("6. küsimus");  
            jb[0].setText("vastus1");
            jb[1].setText("vastus2");
            jb[2].setText("vastus3");
            jb[3].setText("vastus4");  
        }  
        if(current==6)  
        {  
            l.setText("7. küsimus");  
            jb[0].setText("vastus1");
            jb[1].setText("vastus2");
            jb[2].setText("vastus3");  
            jb[3].setText("vastus4");  
        }  
        if(current==7)  
        {  
            l.setText("8. küsimus");  
            jb[0].setText("vastus1");
            jb[1].setText("vastus2");
            jb[2].setText("vastus3");  
            jb[3].setText("vastus4");         
        }  
        if(current==8)  
        {  
            l.setText("9. küsimus");  
            jb[0].setText("vastus1");
            jb[1].setText("vastus2");
            jb[2].setText("vastus3");
            jb[3].setText("vastus4");  
        }  
        if(current==9)  
        {  
            l.setText("10. küsimus");  
            jb[0].setText("vastus1");
            jb[1].setText("vastus2");
            jb[2].setText("vastus3");  
            jb[3].setText("vastus4");  
        }  
        
        l.setBounds(30, 40, 450, 20);
            for(int i=0, j=0; i<=90; i+=30,j++)
		jb[j].setBounds(50,80+i,200,20);
    }
    
    private boolean check() {
	if(current==0) {return(jb[3].isSelected());}
	if(current==1) {return(jb[0].isSelected());}
	if(current==2) {return(jb[3].isSelected());}
	if(current==3) {return(jb[0].isSelected());}
	if(current==4) {return(jb[2].isSelected());}	
	if(current==5) {return(jb[2].isSelected());}		
	if(current==6) {return(jb[1].isSelected());}		
	if(current==7) {return(jb[3].isSelected());}		
	if(current==8) {return(jb[1].isSelected());}		
	if(current==9) {return(jb[2].isSelected());}		
	return false;
	}
    
    private void writeFile(int kys, String aeg, boolean vastus) throws IOException {
        try {
            String kysimus = Integer.toString(kys + 1);
            if (vastus) {
                String tekst = kysimus + ". küsimus " + aeg + " õige vastus\n";
                Files.write(Paths.get("ajad.txt"), tekst.getBytes(), StandardOpenOption.APPEND);
            } else {
               
                String tekst = kysimus + ". küsimus " + aeg + " vale vastus\n";
                Files.write(Paths.get("ajad.txt"), tekst.getBytes(), StandardOpenOption.APPEND);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public static void main(String[] args) {
	new Test("Kordamisküsimused") {};
    }
}

