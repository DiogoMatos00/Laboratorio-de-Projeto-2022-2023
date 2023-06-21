/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ldp_project_gui;



/**
 *
 * @author vasco
 */
public class LDP_Project_GUI {

    public static void main(String[] args) {
  
                 try {

for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        //System.out.println(info); Por alguma razão dá print ao metal também e não sei onde o retirar e adicionar outro
        if ("Nimbus".equals(info.getName())) {
            javax.swing.UIManager.setLookAndFeel(info.getClassName());
            break;
        }
    }
}
catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
    java.util.logging.Logger.getLogger(JFrameMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); 
}
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new JFrameMenuPrincipal().setVisible(true);
            }
        });
                
        
    }
}
