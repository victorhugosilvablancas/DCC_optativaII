/*
 *  HUGOSQL 2022 Victor H Silva
 * Click https://github.com/victorhugosilvablancas/DCC_optativaII.git 
 */

package hugosql.conecta4;

import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Victor Hugo Silva Blancas
 * @college Universidad Autonoma de Queretaro, Mexico
 * @institution 2022 Conacyt, Mexico
 */
public class ConectacuatroFrame extends javax.swing.JFrame {
    private Object[][] data=new Object[0][0];
    private static Boolean JuegoIniciado=false;
    private static Boolean JuegoTerminado=false;

    /** Creates new form Conecta4Frame */
    public ConectacuatroFrame() {
        initComponents();
        Centrar();
        PonListas();
        PonTabla();
    }
    private void Centrar() {
        int x=(this.anchoDePantalla()-this.getWidth())/2;
        int y=(this.altoDePantalla()-this.getHeight())/2;
        setLocation(x,y);
    }
    private void PonTabla() {
        data=new Object[Conectacuatrodata.RENGLONES][Conectacuatrodata.COLUMNAS];
        Conectacuatrodata midato=new Conectacuatrodata();
        for (int i=0;i<Conectacuatrodata.listademonedas.size();i++) {
            midato=Conectacuatrodata.listademonedas.get(i);
            data[midato.renglon][midato.columna]=midato.getMondeda();
        }
        tabla.setModel(new MyTableModel(data,Cabeza));
    }
    private void PonListas() {
        getLista();
        tabla.setRowHeight(70);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pTodo = new javax.swing.JPanel();
        Reiniciar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        estilocasino = new javax.swing.JCheckBox();
        resultado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Optativa II - Conecta 4");

        Reiniciar.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        Reiniciar.setText("Reiniciar");
        Reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReiniciarActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        estilocasino.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        estilocasino.setText("Estilo Casino");
        estilocasino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estilocasinoActionPerformed(evt);
            }
        });

        resultado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        resultado.setForeground(java.awt.Color.red);
        resultado.setText(" ");

        javax.swing.GroupLayout pTodoLayout = new javax.swing.GroupLayout(pTodo);
        pTodo.setLayout(pTodoLayout);
        pTodoLayout.setHorizontalGroup(
            pTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTodoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                    .addGroup(pTodoLayout.createSequentialGroup()
                        .addComponent(Reiniciar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estilocasino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pTodoLayout.setVerticalGroup(
            pTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTodoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Reiniciar)
                    .addComponent(resultado)
                    .addComponent(estilocasino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pTodo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pTodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseReleased
        if (!JuegoTerminado) {
            JuegoIniciado=true;
            int r=tabla.getSelectedRow();
            int i=tabla.getSelectedColumn();
            if (Conectacuatrodata.monedaValida(r, i)) {
                PonTabla();
                if (Conectacuatrodata.YaGano(r,i)) {
                    JuegoTerminado=true;
                    resultado.setText("¡Ya ganaste!");
                } else if (JuegoIniciado) {
                    TiraLaMaquina();
                }
            }
        }
    }//GEN-LAST:event_tablaMouseReleased

    private void Reiniciando() {
        getLista();
        JuegoIniciado=false;
        JuegoTerminado=false;
        resultado.setText(" ");
        PonTabla();
    }
    
    private void ReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReiniciarActionPerformed
        Reiniciando();
    }//GEN-LAST:event_ReiniciarActionPerformed

    private void estilocasinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estilocasinoActionPerformed
        Reiniciando();
    }//GEN-LAST:event_estilocasinoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Reiniciar;
    private javax.swing.JCheckBox estilocasino;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pTodo;
    private javax.swing.JLabel resultado;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables

    private void TiraLaMaquina() {
        Random ran=new Random();
        boolean tirado=false;
        
        if (estilocasino.isSelected()) {
            if (Conectacuatrodata.PuedeGanar()) {
                //System.out.println("puedeganar");
                if (Conectacuatrodata.Indice>=0) {
                    Conectacuatrodata midato=Conectacuatrodata.listademonedas.get(Conectacuatrodata.Indice);
                    midato.moneda=Conectacuatrodata.MONEDA_CREMA;
                    Conectacuatrodata.listademonedas.set(Conectacuatrodata.Indice,midato);
                    tirado=true;
                }
            }
        }
        while (!tirado) {
            int k=ran.nextInt(Conectacuatrodata.listademonedas.size());
            Conectacuatrodata midato=Conectacuatrodata.listademonedas.get(k);
            boolean pasa=Conectacuatrodata.AbajoEsValido(midato.renglon, midato.columna);
            if (pasa) {
                tirado=Conectacuatrodata.EscaqueDisponible(k);
            }
        }

        PonTabla();
    }
    
    private int anchoDePantalla() {
	GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
	return ge.getMaximumWindowBounds().width;
    }
    private int altoDePantalla() {
	GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
	return ge.getMaximumWindowBounds().height;
    }
    
    public class MyRenderer extends DefaultTableCellRenderer {
        private ImageIcon icon;
        public MyRenderer() {
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                    row, column);
            icon=(ImageIcon) data[row][column];
            if (icon!=null) {
                Image dimg = icon.getImage().getScaledInstance(50, 60,Image.SCALE_SMOOTH);
                icon=new ImageIcon(dimg);
            }
            //else setIcon(monedaPurpura());

            return this;
        }
    }
    
    
    public void getLista() {
        Conectacuatrodata.listademonedas=new ArrayList<>();
        Conectacuatrodata midato=new Conectacuatrodata();
        for (int i=0;i<Conectacuatrodata.COLUMNAS;i++) {
            for (int j=0;j<Conectacuatrodata.RENGLONES;j++) {
                midato=new Conectacuatrodata();
                midato.columna=i;
                midato.renglon=j;
                Conectacuatrodata.listademonedas.add(midato);
            }
        }
    }
    
    public String[] Cabeza=new String[] {
        "0",
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
    };
    public Object[][] Tabla() {
        Object[][] mitabla=new Object[Conectacuatrodata.RENGLONES][Conectacuatrodata.COLUMNAS];
        return mitabla;
    }
    
}
