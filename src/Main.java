

import java.awt.*;


import javax.swing.*;

import java.awt.event.*;





public class Main extends JFrame implements ActionListener  {



    public Main() throws Exception{

        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        int width =  Toolkit.getDefaultToolkit().getScreenSize().width;


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(width/2,height/2);

        int Framewidth= this.getWidth();
        int Frameheight= this.getHeight();
        setLocation((width-Framewidth)/2,(height-Frameheight)/2);
        InitialComponents();
        setVisible(true);




    }






    String[] list={"usd","eur","gbp","chf","pln"};

    JComboBox currenecies1=new JComboBox(list);
    JComboBox currenecies2=new JComboBox(list);
    JLabel mycurrency=new JLabel("CURRENCY I HAVE:");
    JLabel wantcurrency=new JLabel("CURRENCY I WANT:");
    JButton convert=new JButton("CONVERT");

    JTextField putamount=new JTextField(6);
    JLabel amount=new JLabel("AMOUNT:");
    JLabel result= new JLabel("RESULT:");
    JLabel value=new JLabel("0");

    JLabel usd=new JLabel("USD:");
    JLabel gbp=new JLabel("GBP:");
    JLabel eur=new JLabel("EUR:");
    JLabel chf=new JLabel("CHF:");
    JLabel rates=new JLabel("RATES");

    JLabel usdsalerate=new JLabel();

    JLabel gbpsalerate=new JLabel();

    JLabel eursalerate=new JLabel();

    JLabel chfsalerate=new JLabel();

    XmlRead currencies;

    public void InitialComponents() throws Exception {



        Panelwithbackground panel =new Panelwithbackground();
        ImageIcon icon = new ImageIcon();
        Image background = icon.getImage();
        panel.setBackgroundImage(background);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();

        currencies=new XmlRead();
        convert.addActionListener(this);


        usdsalerate.setText((currencies.getcurrencyvalue(0))+" PLN");

        gbpsalerate.setText((currencies.getcurrencyvalue(2))+" PLN");

        eursalerate.setText((currencies.getcurrencyvalue(1))+" PLN");

        chfsalerate.setText((currencies.getcurrencyvalue(3))+" PLN");
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.insets=new Insets(10,10,10,10);
        panel.add(amount,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        panel.add(putamount,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        panel.add(mycurrency,gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        panel.add(currenecies1,gbc);
        gbc.gridx=0;
        gbc.gridy=2;
        panel.add(wantcurrency,gbc);
        gbc.gridx=1;
        gbc.gridy=2;
        panel.add(currenecies2,gbc);
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=2;
        gbc.fill=1;
        panel.add(convert,gbc);

        gbc.gridwidth=1;
        gbc.gridx=0;
        gbc.gridy=4;


        panel.add(result,gbc);
        gbc.gridx=1;
        gbc.gridy=4;
        panel.add(value,gbc);



        gbc.gridx=7;
        gbc.gridy=2;
        panel.add(usdsalerate,gbc);
        gbc.gridx=7;
        gbc.gridy=3;
        panel.add(gbpsalerate,gbc);
        gbc.gridx=7;
        gbc.gridy=4;
        panel.add(eursalerate,gbc);
        gbc.gridx=7;
        gbc.gridy=5;
        panel.add(chfsalerate,gbc);


        gbc.gridx=7;
        gbc.gridy=1;
        panel.add(rates,gbc);

        gbc.gridx=6;
        gbc.gridy=2;
        panel.add(usd,gbc);
        gbc.gridx=6;
        gbc.gridy=3;
        panel.add(gbp,gbc);
        gbc.gridx=6;
        gbc.gridy=4;
        panel.add(eur,gbc);
        gbc.gridx=6;
        gbc.gridy=5;
        panel.add(chf,gbc);
        add(panel);




    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==convert){

            int wantindex=0;
            int haveindex=0;
            Double output;
            Double ratio;
            String x= (String) currenecies1.getSelectedItem();
            String y= (String) currenecies2.getSelectedItem();
            Double Amount=Double.valueOf(putamount.getText());
            Double wynik;
            for(int i=0; i<5;i++){

                if(x.equals(currencies.getcurrencymark(i))){
                    haveindex=i;

                }

                if(y.equals(currencies.getcurrencymark(i))){
                    wantindex=i;

                }





            }
            ratio=currencies.getcurrencyvalue(haveindex)/currencies.getcurrencyvalue(wantindex);

            wynik=ratio*Amount;

            value.setText(String.valueOf(wynik));





        }


    }


    class Panelwithbackground extends JPanel {


        public void setBackgroundImage(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            graphics.drawImage(this.backgroundImage,0,0 ,this.getWidth(),this.getHeight(),this);
        }

        private Image backgroundImage;
    }



    public static void main(String[] args) throws Exception {

        Main ramka=new Main();






    }

}




