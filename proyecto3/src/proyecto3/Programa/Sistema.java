/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3.Programa;

import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class Sistema
{
    ArrayList<Insumo> listaInsumos;
    ArrayList<Proveedor> listaProveedores;
    ArrayList<Empleado> listaEmpleados;
    
    public Sistema()
    {
        this.listaInsumos=new ArrayList<>();
        this.listaProveedores=new ArrayList<>();
        this.listaEmpleados=new ArrayList<>();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getLargoListaInsumo()
    {
        return this.listaInsumos.size();
    }
    
    public ArrayList<Insumo> getListaInsumos()
    {
        ArrayList<Insumo> copia= new ArrayList<>();
        for (Insumo insumo : this.listaInsumos)
        {
            copia.add(insumo);
        }
        return copia;
    }
    
    public Insumo getInsumo(String codigo)
    {
        for (Insumo Insumo : listaInsumos)
        {
            if (Insumo.getCodigo().equalsIgnoreCase(codigo))
            {
                return Insumo;
            }
        }
        return null;
    }
    
    public Insumo getInsumoNombre(String codigo)
    {
        for (Insumo Insumo : listaInsumos)
        {
            if (Insumo.getInsumo().equalsIgnoreCase(codigo))
            {
                return Insumo;
            }
        }
        return null;
    }
    
    public boolean addInsumo(Insumo insumo)
    {
        for (Insumo insumoExistente : listaInsumos)
        {
            if(insumoExistente.getCodigo().equalsIgnoreCase(insumo.getCodigo()))
            {
                return false;
            }
        }
        listaInsumos.add(insumo);
        return true;
    }
    
    public boolean removeInsumo(String insumo)
    {
        for (Insumo insumoExistente : listaInsumos)
        {
            if(insumoExistente.getCodigo().equalsIgnoreCase(insumo))
            {
                listaInsumos.remove(insumoExistente);
                return true;
            }
        }
        return false;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public ArrayList<Empleado> getListaEmpleados()
    {
        ArrayList<Empleado> copia= new ArrayList<>();
        for (Empleado insumo : this.listaEmpleados)
        {
            copia.add(insumo);
        }
        return copia;
    }
    
    public boolean addEmpleado(Empleado insumo)
    {
        for (Empleado insumoExistente : listaEmpleados)
        {
            if(insumoExistente.getRut().equalsIgnoreCase(insumo.getRut())){
                return false;
            }
        }
        listaEmpleados.add(insumo);
        return true;
    }
    
    public boolean removeEmpleado(String rut)
    {
        for (Empleado insumoExistente : listaEmpleados)
        {
            if(insumoExistente.getRut().equalsIgnoreCase(rut)){
                listaEmpleados.remove(insumoExistente);
                return true;
            }
        }
        return false;
    }
    
    public Empleado getEmpleado(String rut)
    {
        for (Empleado insumoExistente : listaEmpleados)
        {
            if(insumoExistente.getRut().equalsIgnoreCase(rut))
            {
                return insumoExistente;
            }
        }
        return null;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public ArrayList<Proveedor> getListaProveedor()
    {
        ArrayList<Proveedor> copia= new ArrayList<>();
        for (Proveedor insumo : this.listaProveedores)
        {
            copia.add(insumo);
        }
        return copia;
    }
    
    public boolean addProveedor(Proveedor insumo)
    {
        for (Proveedor insumoExistente : listaProveedores)
        {
            if(insumoExistente.getRut().equalsIgnoreCase(insumo.getRut())){
                return false;
            }
        }
        listaProveedores.add(insumo);
        return true;
    }
    
    public boolean removeProveedor(String insumo)
    {
        for (Proveedor insumoExistente : listaProveedores)
        {
            if(insumoExistente.getRut().equalsIgnoreCase(insumo)){
                listaProveedores.remove(insumoExistente);
                return true;
            }
        }
        return false;
    } 
   
    public Proveedor getProveedor(String rut)
    {
        for(Proveedor p:this.listaProveedores)
        {
            if(rut.equals(p.getRazonSocial()))
            {
                return p;
            }
        }    
        return null;
    }
}

