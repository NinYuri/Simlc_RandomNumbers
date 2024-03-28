package NumAleatorios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

public class Op_ProdMed extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private DecimalFormat df = new DecimalFormat("#.####");
	private DecimalFormat dc = new DecimalFormat("#.##");
	private boolean usogen = false;
	private double media;
	private double datos[];
	private JTextField txtn = new JTextField(4);
	private JTextField txtsem1 = new JTextField(6); 
	private JTextField txtsem2 = new JTextField(6);
	private JLabel lbln = new JLabel("Iteraciones:");
	private JLabel lblsem1 = new JLabel("Primer semilla:");
	private JLabel lblsem2 = new JLabel("Segunda semilla:");
	private JButton bttgenerar = new JButton("Generar");
	private JButton bttmenu = new JButton("Menú");
	private JButton bttborr = new JButton("Borrar");
	private JButton bttmed = new JButton("Prueba Media");
	private JButton bttvar = new JButton("Prueba Varianza");
	private JButton bttregre = new JButton("Regresar");
	private JButton bttva = new JButton("Varianza");
	private JTextArea numArea = new JTextArea(10, 20);
	private JTable tabla;
	private DefaultTableModel mod; 
	private Vector <String> tit = new Vector<String>();
	private Vector<Vector<String>> titulos = new Vector<Vector<String>>();
	
	public Op_ProdMed()
	{
		setSize(700, 700);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		JPanel paneltop = new JPanel();
		paneltop.add(lbln);
		paneltop.add(txtn);
		paneltop.add(lblsem1);
		paneltop.add(txtsem1);
		paneltop.add(lblsem2);
		paneltop.add(txtsem2);
		paneltop.add(bttgenerar);
		paneltop.add(bttmenu);
		paneltop.setBackground(Color.decode("232124108"));
		add(paneltop, BorderLayout.NORTH);
		add(new JScrollPane(numArea), BorderLayout.CENTER);
		JPanel panelbot = new JPanel();
		panelbot.add(bttva);
		panelbot.add(bttvar);
		panelbot.add(bttmed);
		panelbot.add(bttregre);
		panelbot.add(bttborr);
		panelbot.setBackground(Color.decode("232124108"));
		add(panelbot, BorderLayout.SOUTH);
		
		tit.add("n");
		tit.add("X1");
		tit.add("X2");
		tit.add("X1*X2");
		tit.add("Xn");
		tit.add("Na");
		mod = new DefaultTableModel(titulos, tit);
		tabla = new JTable(mod);
		JScrollPane jp = new JScrollPane(tabla);
		add(jp, BorderLayout.CENTER);
		
		bttgenerar.addActionListener(this);
		bttmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new Menu_Principal().setVisible(true);
			}
		});
		bttborr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == bttborr) {
					mod.setRowCount(0);
					txtsem1.setText("");
					txtsem2.setText("");
					txtn.setText("");
				}
			}
		});
		bttva.addActionListener(this);
		bttvar.addActionListener(this);
		bttmed.addActionListener(this);
		bttregre.addActionListener(this);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String sem1 = txtsem1.getText();
		String sem2 = txtsem2.getText();
		String n = txtn.getText();
		
		if(e.getSource() == bttgenerar || e.getSource() == bttregre)
		{
			if(e.getSource() == bttregre)
			{
				mod.setRowCount(0);
				mod.setColumnIdentifiers(tit);
			}
			
			if(n.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Debe ingresar la cantidad de iteraciones", 
						"Error de iteraciones", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
				if(Entero(n) == false)
				{
					JOptionPane.showMessageDialog(this, "Las iteraciones deben ser un número entero", 
							"Error de iteraciones", JOptionPane.ERROR_MESSAGE);
					txtn.setText("");
					return;
				}
				else
					if(Integer.parseInt(n) <= 0)
					{
						JOptionPane.showMessageDialog(this, "La cantidad de iteraciones debe ser mayor a 0", 
								"Error de iteraciones", JOptionPane.ERROR_MESSAGE);
						txtn.setText("");
						return;
					}
			if(sem1.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Debe ingresar una primer semilla", 
						"Error de semilla", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
				if(sem1.length() != 4)
				{
					JOptionPane.showMessageDialog(this, "La primer semilla debe tener, como mínimo, 4 dígitos", 
							"Error de semilla", JOptionPane.ERROR_MESSAGE);
					txtsem1.setText("");
					return;
				}
				else
					if(Entero(sem1) == false)
					{
						JOptionPane.showMessageDialog(this, "La primer semilla debe ser un número entero", 
								"Error de semilla", JOptionPane.ERROR_MESSAGE);
						txtsem1.setText("");
						return;
					}
					else
						if(Integer.parseInt(sem1) <= 0)
						{
							JOptionPane.showMessageDialog(this, "La primer semilla debe ser mayor a 0", 
									"Error de semilla", JOptionPane.ERROR_MESSAGE);
							txtsem1.setText("");
							return;
						}
			if(sem2.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Debe ingresar una segunda semilla", 
						"Error de semilla", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
				if(sem2.length() != 4)
				{
					JOptionPane.showMessageDialog(this, "La segunda semilla debe tener, como mínimo, 4 dígitos", 
							"Error de semilla", JOptionPane.ERROR_MESSAGE);
					txtsem2.setText("");
					return;
				}
				else
					if(Entero(sem2) == false)
					{
						JOptionPane.showMessageDialog(this, "La segunda semilla debe ser un número entero", 
								"Error de semilla", JOptionPane.ERROR_MESSAGE);
						txtsem2.setText("");
						return;
					}
					else
						if(Integer.parseInt(sem2) <= 0)
						{
							JOptionPane.showMessageDialog(this, "La segunda semilla debe ser mayor a 0", 
									"Error de semilla", JOptionPane.ERROR_MESSAGE);
							txtsem2.setText("");
							return;
						}
			
				int semilla1 = Integer.parseInt(sem1);
				int semilla2= Integer.parseInt(sem2);
				int can = Integer.parseInt(n);
				int xn, cadxn, ini, fin;
				double alet, na;
				String cad, ale;
				datos = new double[can];
				
				for(int i = 1; i <= can; i++) 
				{
					Vector<Object> dt=new Vector<Object>();
	 				dt.add(i);
	 				dt.add(semilla1);
	 				dt.add(semilla2);
	 				
					xn = semilla1 * semilla2; 
					cad = Integer.toString(xn);
		            cadxn = cad.length();
					while(cadxn < 8) 
					{
			            cad = cad + "0";
			            cadxn++;
			        }
			        ini = (cadxn / 2) - 2;
			        fin = (cadxn / 2) + 2;
			        ale = cad.substring(ini, fin);
			        alet = Double.parseDouble(ale);
			        semilla1 = semilla2;
			        semilla2 = (int)alet;
			        na = alet /= 10000;
			        
			        dt.add(xn);
	 		        dt.add(ale);
	 		        dt.add(na);
	 		        datos[i - 1] = na;
	 		        mod.addRow(dt);
				}
	    		double sum = 0;
	    		for(double valor : datos)
	    			sum += valor;
	    		double prom = sum / can;
	    		String res = dc.format(prom);
	    		media = Double.parseDouble(res);
	    		usogen = true;
		}
		else
			if(e.getSource() == bttmed)
			{
				if(n.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Debe ingresar la cantidad de iteraciones", 
							"Error de prueba de media", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
					if(sem1.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Debe ingresar una primer semilla inicial", 
								"Error de prueba de media", JOptionPane.ERROR_MESSAGE);
						return;
					}
					else
						if(sem2.isEmpty())
						{
							JOptionPane.showMessageDialog(null, "Debe ingresar una segunda semilla inicial", 
									"Error de prueba de media", JOptionPane.ERROR_MESSAGE);
							return;
						}
						else
							if(!usogen)
							{
								JOptionPane.showMessageDialog(null, "Debe generar los números aleatorios necesarios para la prueba", 
										"Error de prueba de media", JOptionPane.ERROR_MESSAGE);
								return;
							}
				
				String nc = JOptionPane.showInputDialog("Ingrese un Nivel de Confianza:");
				if(nc != null && !nc.isEmpty())
				{
					int por = Integer.parseInt(nc);
					int nvp = 100 - por;
					double alpha = (Double.parseDouble(String.valueOf(nvp)) / 2) / 100;
					NormalDistribution normalDist = new NormalDistribution();
			        double z = Math.abs(normalDist.inverseCumulativeProbability(alpha));
			        double z2 = 1 / Math.sqrt(12*Integer.parseInt(n));
			        double mult = z * z2;
					
					mod.setRowCount(0);
					mod.setColumnIdentifiers(new Object[]{"","","PRUEBA DE MEDIA","",""});
					mod.addRow(new Object[] {});
					for(int i = 0; i < 7; i++)
					{
						if(i == 0)
							mod.addRow(new Object[] {"n", n, "", "Media", media});
						else
							if(i == 1)
								mod.addRow(new Object[] {"NC", por + "%", "", "Za/2", z});
							else
								if(i == 2)
									mod.addRow(new Object[] {"1 - a", nvp + "%", "", "1/Raiz(12n)", z2});
								else
									if(i == 3)
										mod.addRow(new Object[] {"a/2", alpha, "", "Za/2*1/Raiz(12n)", mult});
									else
										if(i == 4)
											mod.addRow(new Object[] {});
										else
											if(i == 5)
												mod.addRow(new Object[] {"","","","Li", 0.5 - mult});
											else
												if(i == 6)
													mod.addRow(new Object[] {"", "", "", "Ls", 0.5 + mult});
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Cálculo de prueba cancelada");
			}
			else
				if(e.getSource() == bttvar)
				{
					if(n.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Debe ingresar la cantidad de iteraciones", 
								"Error de prueba de varianza", JOptionPane.ERROR_MESSAGE);
						return;
					}
					else
						if(sem1.isEmpty())
						{
							JOptionPane.showMessageDialog(null, "Debe ingresar una primer semilla inicial", 
									"Error de prueba de varianza", JOptionPane.ERROR_MESSAGE);
							return;
						}
						else
							if(sem2.isEmpty())
							{
								JOptionPane.showMessageDialog(null, "Debe ingresar una segunda semilla inicial", 
										"Error de prueba de varianza", JOptionPane.ERROR_MESSAGE);
								return;
							}
							else
								if(!usogen)
								{
									JOptionPane.showMessageDialog(null, "Debe generar los números aleatorios necesarios para la prueba", 
											"Error de prueba de varianza", JOptionPane.ERROR_MESSAGE);
									return;
								}
				
					int can = Integer.parseInt(n);
					String nc = JOptionPane.showInputDialog("Ingrese un Nivel de Confianza:");
					if(nc != null && !nc.isEmpty())
					{
						int por = Integer.parseInt(nc);
						int nvp = 100 - por;
						double alpha = (Double.parseDouble(String.valueOf(nvp)) / 2) / 100;
						double alp = 1 - alpha;
						Variance variance = new Variance();
						double var = variance.evaluate(datos);
						ChiSquaredDistribution distribucionChi = new ChiSquaredDistribution(can - 1);
						double vchi1 = distribucionChi.inverseCumulativeProbability(alpha);
						double vchi2 = distribucionChi.inverseCumulativeProbability(alp);
						double li = vchi1 / (12*(can - 1)), ls = vchi2 / (12*(can - 1));
					
						mod.setRowCount(0);
						mod.setColumnIdentifiers(new Object[]{"","","PRUEBA DE VARIANZA","",""});
						mod.addRow(new Object[] {});
						for(int i = 0; i <= 7; i++)
						{
							if(i == 0)
								mod.addRow(new Object[] {"n", n, "", "Media", media});
							else
								if(i == 1)
									mod.addRow(new Object[] {"NC", por + "%", "", "Varianza", var});
								else
									if(i == 2)
										mod.addRow(new Object[] {"1-a", nvp + "%",});
									else
										if(i == 3)
											mod.addRow(new Object[] {"a/2", alpha, "", "Li", li});
										else
											if(i == 4)
												mod.addRow(new Object[] {"1 - a/2", alp, "", "Ls", ls});
											else
												if(i == 5)
													mod.addRow(new Object[] {});
												else
													if(i == 6)
														mod.addRow(new Object[] {"a/2", vchi2});
													else
														if(i == 7)
															mod.addRow(new Object[] {"1 - a/2", vchi1});
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Cálculo de prueba cancelada");
				}
				else
					if(e.getSource() == bttva)
					{
						if(n.isEmpty())
						{
							JOptionPane.showMessageDialog(null, "Debe ingresar la cantidad de iteraciones", 
									"Error de varianza", JOptionPane.ERROR_MESSAGE);
							return;
						}
						else
							if(sem1.isEmpty())
							{
								JOptionPane.showMessageDialog(null, "Debe ingresar una primer semilla inicial", 
										"Error de varianza", JOptionPane.ERROR_MESSAGE);
								return;
							}
							else
								if(sem2.isEmpty())
								{
									JOptionPane.showMessageDialog(null, "Debe ingresar una segunda semilla inicial", 
											"Error de varianza", JOptionPane.ERROR_MESSAGE);
									return;
								}
								else
									if(!usogen)
									{
										JOptionPane.showMessageDialog(null, "Debe generar los números aleatorios necesarios para la varianza", 
												"Error de varianza", JOptionPane.ERROR_MESSAGE);
										return;
									}
						double suma = 0;
						String ric, res;
						mod.setRowCount(0);
						mod.setColumnIdentifiers(new Object[]{"", "VARIANZA","",""});
						for(int i = 0; i < Integer.parseInt(txtn.getText()); i++)
						{
							ric = df.format(datos[i] - media);
							res = df.format(Math.pow((datos[i] - media), 2));
							mod.addRow(new Object[] {"", ric, res});
							suma += Math.pow(datos[i] - media, 2);
						}
						mod.addRow(new Object[] {});
						mod.addRow(new Object[] {"Media", media});
						mod.addRow(new Object[] {"Varianza", suma / (Integer.parseInt(n) - 1)});
					}
	}
	
	private static boolean Entero(String n) 
	{
	    try 
	    {
	        Integer.parseInt(n);
	        return true;
	    } 
	    catch (NumberFormatException e) 
	    {
	        return false;
	    }
	}
}