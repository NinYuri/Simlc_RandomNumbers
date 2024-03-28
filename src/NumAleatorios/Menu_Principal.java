package NumAleatorios;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu_Principal extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JLabel lblbv = new JLabel("BIENVENIDO");
	private JLabel lblop = new JLabel("¿QUÉ GENERADOR DESEA USAR?");
	private JButton btncdmd = new JButton("Cuadrados Medios");
	private JButton btnpdmd = new JButton("Productos Medios");
	private JButton btnmlcn = new JButton("Multiplicador Constante");
	private JButton btnagln = new JButton("Algoritmo Lineal");
	private JButton btncgml = new JButton("Congruencial Multiplicativo");
	private JButton btncgad = new JButton("Congruencial Aditivo");
	private JButton btncgcd = new JButton("Congruencial Cuadrático");
	private JButton btncgmx = new JButton("Congruencial Mixto");
	
	public Menu_Principal()
	{
		setTitle("GENERADORES DE NÚMEROS ALEATORIOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 450);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.decode("232124108"));	
        Font nvbv = new Font("Century Schoolbook", Font.BOLD, 50);
        lblbv.setFont(nvbv);
        Font nvop = new Font("Century Schoolbook", Font.BOLD, 15);
        lblop.setFont(nvop);
        
		btncdmd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Op_CuadMed().setVisible(true);
				setVisible(false);
			}
		});
		btnpdmd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Op_ProdMed().setVisible(true);
				setVisible(false);
			}
		});
		btnmlcn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Op_MultCons().setVisible(true);
				setVisible(false);
			}
		});
		btnagln.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Op_AlgLin().setVisible(true);
				setVisible(false);
			}
		});
		btncgml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Op_CongMult().setVisible(true);
				setVisible(false);
			}
		});
		btncgad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Op_CongAdit().setVisible(true);
				setVisible(false);
			}
		});
		btncgcd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Op_CongCuad().setVisible(true);
				setVisible(false);
			}
		});
		btncgmx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Op_CongMix().setVisible(true);
				setVisible(false);
			}
		});
		
		Container contenedor = getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.add(lblbv);
        contenedor.add(lblop);
        contenedor.add(Box.createVerticalStrut(25));
		contenedor.add(btncdmd);
		contenedor.add(Box.createVerticalStrut(10));
		contenedor.add(btnpdmd);
		contenedor.add(Box.createVerticalStrut(10));
		contenedor.add(btnmlcn);
		contenedor.add(Box.createVerticalStrut(10));
		contenedor.add(btnagln);
		contenedor.add(Box.createVerticalStrut(10));
		contenedor.add(btncgml);
		contenedor.add(Box.createVerticalStrut(10));
		contenedor.add(btncgad);
		contenedor.add(Box.createVerticalStrut(10));
		contenedor.add(btncgcd);
		contenedor.add(Box.createVerticalStrut(10));
		contenedor.add(btncgmx);
		contenedor.add(Box.createVerticalStrut(10));
		
		lblbv.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblop.setAlignmentX(Component.CENTER_ALIGNMENT);
		btncdmd.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnpdmd.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnmlcn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnagln.setAlignmentX(Component.CENTER_ALIGNMENT);
		btncgml.setAlignmentX(Component.CENTER_ALIGNMENT);
		btncgad.setAlignmentX(Component.CENTER_ALIGNMENT);
		btncgcd.setAlignmentX(Component.CENTER_ALIGNMENT);
		btncgmx.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	public static void main(String[] args)
	{
		new Menu_Principal().setVisible(true);
	}
}