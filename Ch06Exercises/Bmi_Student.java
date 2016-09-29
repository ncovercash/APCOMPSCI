
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.DecimalFormat;
// you only need to work in the calculateBMI method
/*The BMI program computes a person’s body mass index (BMI). 
 * BMI is defined as the weight, expressed in kilograms, 
 * divided by the square of the height expressed in meters. 
 * (One inch is 0.0254 meters; one pound is 0.454 kilograms.) 
 * Supply the missing code for the calculateBmi method, 
 * which takes a weight in pounds and height in inches as parameters 
 * and returns the body mass index.
 */

public class Bmi_Student extends JFrame
  implements ActionListener
{
  JTextField inputLbs, inputInches, displayBmi;

  public Bmi_Student()
  {
    super("BMI Calculator");
    JLabel labelLbs = new JLabel("Weight (lbs):", SwingConstants.RIGHT);
    inputLbs = new JTextField(5);
    JLabel labelInches = new JLabel("Height (inches):", SwingConstants.RIGHT);
    inputInches = new JTextField(5);
    JLabel labelBmi = new JLabel("BMI = ", SwingConstants.RIGHT);
    displayBmi = new JTextField(5);
    displayBmi.setEditable(false);
    JButton go = new JButton("Compute");
    go.addActionListener(this);

    Container c = getContentPane();
    c.setBackground(Color.white);
    JPanel p = new JPanel();
    p.setLayout(new GridLayout(3, 2, 5, 5));
    p.add(labelLbs);
    p.add(inputLbs);
    p.add(labelInches);
    p.add(inputInches);
    p.add(labelBmi);
    p.add(displayBmi);
    c.add(p, BorderLayout.CENTER);
    c.add(go, BorderLayout.SOUTH);
  }

  public void actionPerformed(ActionEvent e)
  {
    int lbs = Integer.parseInt(inputLbs.getText());
    int inches = Integer.parseInt(inputInches.getText());
    double bmi = calculateBmi(lbs, inches);
    DecimalFormat df = new DecimalFormat("00.0");
    displayBmi.setText(df.format(bmi));
  }

  // Returns BMI equal to weight in kilograms divided
  // over squared height in meters.
  private double calculateBmi(int lbs, int inches)
  {
    double meterH, kg;

    kg = lbs*0.45359237;
    meterH = 0.0254*inches;
    
    return (kg)/(meterH*meterH);
  }

  public static void main(String[] args)
  {
    Bmi_Student w = new Bmi_Student();
    w.setBounds(300, 300, 300, 160);
    w.setDefaultCloseOperation(EXIT_ON_CLOSE);
    w.setVisible(true);
  }
}
