/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3.Programa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mario
 */
public class Eso
    {
        ObservableList<Object> dataInsumos = FXCollections.observableArrayList();
        ObservableList<Object> dataProveedores = FXCollections.observableArrayList();
        ObservableList<Object> dataEmpleados = FXCollections.observableArrayList();
        int numgenerado;

        public Eso()
        {
        }

        public int getNumgenerado()
        {
            return numgenerado;
        }

        public void setNumgenerado(int numgenerado)
        {
            this.numgenerado = numgenerado;
        }
        
        public void setDataInsumos(ObservableList<Object> numgenerado)
        {
            this.dataInsumos = numgenerado;
        }
        
        public void setDataProveedores(ObservableList<Object> numgenerado)
        {
            this.dataProveedores = numgenerado;
        }
        
        public void setDataEmpleados(ObservableList<Object> numgenerado)
        {
            this.dataEmpleados = numgenerado;
        }
        
        public ObservableList<Object> getDataInsumos()
        {
            return dataInsumos;
        }
        public ObservableList<Object> getDataProveedores()
        {
            return dataInsumos;
        }
        public ObservableList<Object> getDataEmpleados()
        {
            return dataInsumos;
        }
    }
