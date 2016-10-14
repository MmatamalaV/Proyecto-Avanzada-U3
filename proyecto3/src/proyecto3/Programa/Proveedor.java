/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3.Programa;

/**
 *
 * @author mario
 */
public class Proveedor
{
    private String rut;
    private String razonSocial;
    private String giro;
    private String direccion;
    private String region;
    private String comuna;
    private String ciudadLocalidad;
    private String telefonoContacto;
    private String Email;
    private String infoAdicional;

    public Proveedor(String rut, String razonSocial, String giro, String direccion, String region, String comuna, String ciudadLocalidad, String telefonoContacto, String Email, String infoAdicional)
    {
        this.rut = rut;
        this.razonSocial = razonSocial;
        this.giro = giro;
        this.direccion = direccion;
        this.region = region;
        this.comuna = comuna;
        this.ciudadLocalidad = ciudadLocalidad;
        this.telefonoContacto = telefonoContacto;
        this.Email = Email;
        this.infoAdicional = infoAdicional;
    }

    public String getRut()
    {
        return rut;
    }

    public void setRut(String rut)
    {
        this.rut = rut;
    }

    public String getRazonSocial()
    {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial)
    {
        this.razonSocial = razonSocial;
    }

    public String getGiro()
    {
        return giro;
    }

    public void setGiro(String giro)
    {
        this.giro = giro;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getComuna()
    {
        return comuna;
    }

    public void setComuna(String comuna)
    {
        this.comuna = comuna;
    }

    public String getCiudadLocalidad()
    {
        return ciudadLocalidad;
    }

    public void setCiudadLocalidad(String ciudadLocalidad)
    {
        this.ciudadLocalidad = ciudadLocalidad;
    }

    public String getTelefonoContacto()
    {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto)
    {
        this.telefonoContacto = telefonoContacto;
    }

    public String getEmail()
    {
        return Email;
    }

    public void setEmail(String Email)
    {
        this.Email = Email;
    }

    public String getInfoAdicional()
    {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional)
    {
        this.infoAdicional = infoAdicional;
    }
    
    
    
}
