
package practica;

import Controller.CtrlExamen;
import DAO.ExamenDAO;
import Model.Examen;
import View.frmExamen;


public class Practica {

   
    public static void main(String[] args) {
      Examen objModel = new Examen();
      ExamenDAO objDAO = new ExamenDAO();
      frmExamen objView = new frmExamen();
      CtrlExamen objCtrl= new CtrlExamen(objView,objModel,objDAO);
      
        objCtrl.iniciar();
        objView.setVisible(true);
    }
    
}
