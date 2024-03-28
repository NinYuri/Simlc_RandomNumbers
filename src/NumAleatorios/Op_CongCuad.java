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

public class Op_CongCuad extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private DecimalFormat df = new DecimalFormat("#.####");
	private DecimalFormat dc = new DecimalFormat("#.##");
	private boolean usogen = false;
	private double media;
	private double datos[];
	private JTextField txtn = new JTextField(4);
	private JTextField txtsem = new JTextField(6);
	private JTextField txta = new JTextField(4);
	private JTextField txtc = new JTextField(4);
	private JTextField txtmod = new JTextField(4);
	private JLabel lbln = new JLabel("Iteraciones:");
	private JLabel lblsem = new JLabel("Semilla:");
	private JLabel lbla = new JLabel("Valor de a:");
	private JLabel lblc = new JLabel("Valor de c:");
	private JLabel lblm = new JLabel("Potencia del módulo:");
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
	
	public Op_CongCuad()
	{
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		JPanel paneltop = new JPanel();
		paneltop.add(lbln);
		paneltop.add(txtn);
		paneltop.add(lblsem);
		paneltop.add(txtsem);
		paneltop.add(lbla);
		paneltop.add(txta);
		paneltop.add(lblc);
		paneltop.add(txtc);
		paneltop.add(lblm);
		paneltop.add(txtmod);
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
		tit.add("m");
		tit.add("a");
		tit.add("b");
		tit.add("c");
		tit.add("X0");
		tit.add("aX2+bX+c");
		tit.add("MOD");
		tit.add("Ri");
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
					txtn.setText("");
					txtsem.setText("");
					txta.setText("");
					txtc.setText("");
					txtmod.setText("");
				}
			}
		});
		bttva.addActionListener(this);
		bttvar.addActionListener(this);
		bttmed.addActionListener(this);
		bttregre.addActionListener(this);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) 
	{	
		String n = txtn.getText();
		String sem = txtsem.getText();
		String a = txta.getText();
		String c = txtc.getText();
		String pot = txtmod.getText();
		
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
			if(sem.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Debe ingresar una semilla", 
						"Error de semilla", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
				if(Entero(sem) == false)
				{
					JOptionPane.showMessageDialog(this, "La semilla debe ser un número entero", 
							"Error de semilla", JOptionPane.ERROR_MESSAGE);
					txtsem.setText("");
					return;
				}
				else
					if(Integer.parseInt(sem) <= 0)
					{
						JOptionPane.showMessageDialog(this, "La semilla debe ser mayor a 0", 
								"Error de semilla", JOptionPane.ERROR_MESSAGE);
						txtsem.setText("");
						return;
					}
			if(a.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Debe ingresar un valor de a", 
						"Error de constantes", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
				if(Entero(a) == false)
				{
					JOptionPane.showMessageDialog(this, "El valor de a debe ser un número entero", 
							"Error de constantes", JOptionPane.ERROR_MESSAGE);
					txta.setText("");
					return;
				}
				else
					if(Integer.parseInt(a) <= 0)
					{
						JOptionPane.showMessageDialog(this, "El valor de a debe ser mayor a 0", 
								"Error de constantes", JOptionPane.ERROR_MESSAGE);
						txta.setText("");
						return;
					}
					else
						if(Par(Integer.parseInt(a)) == false)
						{
							JOptionPane.showMessageDialog(this, "El valor de a debe ser un número par", 
									"Error de constantes", JOptionPane.ERROR_MESSAGE);
							txta.setText("");
							return;
						}
			if(c.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Debe ingresar un valor de c", 
						"Error de constantes", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
				if(Entero(c) == false)
				{
					JOptionPane.showMessageDialog(this, "El valor de c debe ser un número entero", 
							"Error de constantes", JOptionPane.ERROR_MESSAGE);
					txtc.setText("");
					return;
				}
				else
					if(Integer.parseInt(c) <= 0)
					{
						JOptionPane.showMessageDialog(this, "El valor de c debe ser mayor a 0", 
								"Error de constantes", JOptionPane.ERROR_MESSAGE);
						txtc.setText("");
						return;
					}
					else
						if(Par(Integer.parseInt(c)) == true)
						{
							JOptionPane.showMessageDialog(this, "El valor de c debe ser impar", 
									"Error de constantes", JOptionPane.ERROR_MESSAGE);
							txtc.setText("");
							return;
						}
			if(pot.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Debe ingresar una potencia para el módulo", 
						"Error de potencia del módulo", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
				if(Entero(pot) == false)
				{
					JOptionPane.showMessageDialog(this, "La potencia para el módulo debe ser un número entero", 
							"Error de potencia del módulo", JOptionPane.ERROR_MESSAGE);
					txtmod.setText("");
					return;
				}
				else
				{
					if(Integer.parseInt(pot) <= 0)
					{
						JOptionPane.showMessageDialog(this, "La potencia para el módulo debe ser mayor a 0", 
								"Error de multiplicador constante", JOptionPane.ERROR_MESSAGE);
						txtmod.setText("");
						return;
					}
					if(Integer.parseInt(pot) != 6)
					{
						JOptionPane.showMessageDialog(null, "De preferencia colocar una potencia 6", "Información", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			
			int can = Integer.parseInt(n);
			int semilla = Integer.parseInt(sem);
			int ca = Integer.parseInt(a);
			int cc = Integer.parseInt(c);
			int potencia = Integer.parseInt(pot);
			int m = (int)Math.pow(2, potencia), xn;
			double alet, na, ri;
			String ric;
			datos = new double[can];
			
			for(int i = 1; i <= can; i++) 
			{
				Vector<Object> dt=new Vector<Object>();
				dt.add(i);
				dt.add(m);
				dt.add(a);
				dt.add(27);
				dt.add(c);
				dt.add(semilla);
			
				xn = ((int)Math.pow(semilla, 2)*ca) + (27*semilla) + cc;
				alet = xn % m;
				na = alet / (m - 1);
			    semilla = (int)alet;    
			    ric = df.format(na);
			    ri = Double.parseDouble(ric);
			    
			    dt.add(xn);
			    dt.add((int)alet);
				dt.add(ri);
 		        datos[i - 1] = ri;
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
					if(sem.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Debe ingresar una semilla inicial", 
								"Error de prueba de media", JOptionPane.ERROR_MESSAGE);
						return;
					}
					else
						if(a.isEmpty())
						{
							JOptionPane.showMessageDialog(this, "Debe ingresar un valor de a", 
									"Error de prueba de media", JOptionPane.ERROR_MESSAGE);
							return;
						}
						else
							if(c.isEmpty())
							{
								JOptionPane.showMessageDialog(this, "Debe ingresar un valor de c", 
										"Error de prueba de media", JOptionPane.ERROR_MESSAGE);
								return;
							}
							else
								if(pot.isEmpty())
								{
									JOptionPane.showMessageDialog(this, "Debe ingresar una potencia para el módulo", 
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
						if(sem.isEmpty())
						{
							JOptionPane.showMessageDialog(null, "Debe ingresar una semilla inicial", 
									"Error de prueba de varianza", JOptionPane.ERROR_MESSAGE);
							return;
						}
						else
							if(a.isEmpty())
							{
								JOptionPane.showMessageDialog(this, "Debe ingresar un valor de a", 
										"Error de prueba de varianza", JOptionPane.ERROR_MESSAGE);
								return;
							}
							else
								if(c.isEmpty())
								{
									JOptionPane.showMessageDialog(this, "Debe ingresar un valor de c", 
											"Error de prueba de varianza", JOptionPane.ERROR_MESSAGE);
									return;
								}
								else
									if(pot.isEmpty())
									{
										JOptionPane.showMessageDialog(this, "Debe ingresar una potencia para el módulo", 
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
							if(sem.isEmpty())
							{
								JOptionPane.showMessageDialog(null, "Debe ingresar una semilla inicial", 
										"Error de varianza", JOptionPane.ERROR_MESSAGE);
								return;
							}
							else
								if(a.isEmpty())
								{
									JOptionPane.showMessageDialog(this, "Debe ingresar un valor de a", 
											"Error de varianza", JOptionPane.ERROR_MESSAGE);
									return;
								}
								else
									if(c.isEmpty())
									{
										JOptionPane.showMessageDialog(this, "Debe ingresar un valor de c", 
												"Error de varianza", JOptionPane.ERROR_MESSAGE);
										return;
									}
									else
										if(pot.isEmpty())
										{
											JOptionPane.showMessageDialog(this, "Debe ingresar una potencia para el módulo", 
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
	
	private static boolean Par(int a)
	{
		if(a % 2 == 0)
			return true;
		else
			return false;
	}
}