/*
 *  HUGOSQL 2022 Victor H Silva
 * Click https://github.com/victorhugosilvablancas/DCC_optativaII.git 
 */

package hugosql.conecta4;

import java.util.List;
import java.util.ArrayList;
import javax.swing.Icon;

/**
 *
 * @author Victor Hugo Silva Blancas
 * @college Universidad Autonoma de Queretaro, Mexico
 * @institution 2022 Conacyt, Mexico
 */
public class Conectacuatrodata {
    public Integer renglon;
    public Integer columna;
    public Integer moneda;
    
    public static List<Conectacuatrodata> listademonedas=new ArrayList<>();
    
    public static final int TRES=3;
    public static final int CUATRO=4;
    public static final int COLUMNAS=7;
    public static final int RENGLONES=6;
    
    public Conectacuatrodata() {
        renglon=0;
        columna=0;
        moneda=MONEDA_BLANCA;
    }
    public Icon getMondeda() {
        return monedas[moneda];
    }
    public String cadena() {
        return renglon + ":"
                + columna + ","
                + monedasStr[moneda]
                ;
    }
    public String coordenadas() {
        return renglon+","+columna;
    }
    public Boolean esBlanca() {
        return moneda == MONEDA_BLANCA;
    }
    public Boolean esPurpura() {
        return moneda == MONEDA_PURPURA;
    }
    public Boolean esCrema() {
        return moneda == MONEDA_CREMA;
    }
    public static final int MONEDA_BLANCA   =0;
    public static final int MONEDA_PURPURA  =1;
    public static final int MONEDA_CREMA    =2;
    public Icon[] monedas=new Icon[] {
        new javax.swing.ImageIcon(getClass().getResource("/hugosql/imagenes/moneda_blanca.png")),
        new javax.swing.ImageIcon(getClass().getResource("/hugosql/imagenes/moneda_purpura.png")),
        new javax.swing.ImageIcon(getClass().getResource("/hugosql/imagenes/moneda_crema.png")),
    };
    public static String[] monedasStr=new String[] {
        "blanca",
        "purpura",
        "crema",
    };
    
    /**
     * Determina si en el escaque la moneda es válida.
     * 
     * @param ren renglón
     * @param col columna
     * @return true si en la posición puede colocarse una moneda púrpura
     */
    public static Boolean monedaValida(Integer ren,Integer col) {
        Boolean esValida=false;
        Boolean posicionCorrecta=AbajoEsValido(ren,col);
        
        if (posicionCorrecta) {
            Conectacuatrodata midato=new Conectacuatrodata();
            //saber si el escaque es blanco
            Boolean estaEnBlanco=false;
            int iposicion=0;
            for (int i=0;i<Conectacuatrodata.listademonedas.size();i++) {
                midato=Conectacuatrodata.listademonedas.get(i);
                if (midato.columna.equals(col) && midato.renglon.equals(ren)) {
                    estaEnBlanco=midato.esBlanca();
                    iposicion=i;
                    break;
                }
            }
            if (estaEnBlanco) {
                midato.moneda=MONEDA_PURPURA;
                Conectacuatrodata.listademonedas.set(iposicion,midato);
                esValida=true;
            }
        }
        return esValida;
    }
    /**
     * Retorna el índice de la lista que corresponde al renglón y la columna
     * @param ren coordenada y
     * @param col coordenada x
     * @return Indice de listademonedas
     */
    public static Integer getIndice(Integer ren,Integer col) {
        Integer iposicion=-1;
        
        Conectacuatrodata midato=new Conectacuatrodata();
        for (int i=0;i<Conectacuatrodata.listademonedas.size();i++) {
            midato=Conectacuatrodata.listademonedas.get(i);
            if (midato.columna.equals(col) && midato.renglon.equals(ren)) {
                iposicion=i;
                break;
            }
        }
        return iposicion;
    }
    
    /**
     * Le indica a la computadora si el escaque está disponible.
     * 
     * @param posicion
     * @return 
     */
    public static Boolean EscaqueDisponible(int posicion) {
        Boolean disponible=false;
        Conectacuatrodata midato=new Conectacuatrodata();
        for (int i=0;i<Conectacuatrodata.listademonedas.size();i++) {
            if (i==posicion) {
                midato=Conectacuatrodata.listademonedas.get(i);
                disponible=midato.esBlanca();
                if (disponible) {
                    midato.moneda=MONEDA_CREMA;
                    Conectacuatrodata.listademonedas.set(i,midato);
                }
                break;
            }
        }
        return disponible;
    }
    
    public static Boolean Encontrado=false;
    public static Integer Indice=-1;
    /** 
     * Devuelve el dato de la lista que corresponde a las coordenadas.
     * 
     * @param ren renglón
     * @param col columna
     * @return el dato tipo Conectacuatrodata correspondiente
     */
    public static Conectacuatrodata getDato(Integer ren,Integer col) {
        Conectacuatrodata midato=new Conectacuatrodata();
        Encontrado=false;
        Indice=-1;
        for (int i=0;i<Conectacuatrodata.listademonedas.size();i++) {
            if (Conectacuatrodata.listademonedas.get(i).columna.equals(col) 
                    && Conectacuatrodata.listademonedas.get(i).renglon.equals(ren)) {
                midato=Conectacuatrodata.listademonedas.get(i);
                Encontrado=true;
                Indice=i;
                break;
            }
        }
        return midato;
    }
    /**
     * Verifica que la posición seleccionada sea válida.
     * 
     * @param ren renglón
     * @param col columna
     * @return true si el escaque de abajo es el primer piso o tiene ya una moneda
     */
    public static Boolean AbajoEsValido(Integer ren,Integer col) {
        Boolean esValido=false;
        int irenglon=RENGLONES-1;
        esValido=ren==irenglon;
        if (!esValido) {
            Conectacuatrodata adato=getDato(ren+1,col);
            esValido=!adato.esBlanca();
        }
        return esValido;
    }
    
    /**
     * Determina si la posición produce un eje ganador en:
     * 1. banda
     * 2. barra
     * 3. faja
     * 4. palo
     * 
     * @param ren la coordenada y
     * @param col la coordenada x
     * @return true si en uno de los ejes se agrupan cuadro monedas iguales
     */
    public static Boolean YaGano(int ren,int col) {
        Boolean ganado=false;
        Conectacuatrodata moneda1=new Conectacuatrodata();
        Conectacuatrodata moneda2=new Conectacuatrodata();
        Conectacuatrodata moneda3=new Conectacuatrodata();
        Conectacuatrodata moneda4=new Conectacuatrodata();
        int min_ren=ren-3;
        int max_ren=ren+3;
        int min_col=col-3;
        int max_col=col+3;
        int contador=0;
        int iren=0;
        int icol=0;
        
        //1.BANDA
        if (!ganado) {
            contador=0;
            iren=min_ren;
            icol=min_col;
            int iterante=0;
            while (iterante<8) {
                moneda1=getDato(iren,icol);
                if (moneda1.esPurpura()) contador++;
                if (contador==CUATRO) {
                    ganado=true;
                    break;
                }
                iren++;
                icol++;
                iterante++;
            } //while
        }
        //2.BARRA
        if (!ganado) {
            contador=0;
            iren=min_ren;
            icol=col+3;
            int iterante=0;
            while (iterante<8) {
                moneda1=getDato(iren,icol);
                if (moneda1.esPurpura()) contador++;
                if (contador==CUATRO) {
                    ganado=true;
                    break;
                }
                iren++;
                icol--;
                iterante++;
            } //while
        }
        //3.FAJA
        if (!ganado) {
            contador=0;
            for (icol=min_col;icol<max_col;icol++) {
                moneda1=getDato(ren,icol);
                if (moneda1.esPurpura()) contador++;
                moneda2=getDato(ren,icol+1);
                if (moneda2.esPurpura()) contador++;
                moneda3=getDato(ren,icol+2);
                if (moneda3.esPurpura()) contador++;
                moneda4=getDato(ren,icol+3);
                if (moneda4.esPurpura()) contador++;
                if (contador==CUATRO) ganado=true;
                contador=0;
            }
        }
        //4.PALO
        if (!ganado) {
            contador=0;
            for (iren=min_ren;iren<max_ren;iren++) {
                moneda1=getDato(iren,col);
                if (moneda1.esPurpura()) contador++;
                moneda2=getDato(iren+1,col);
                if (moneda2.esPurpura()) contador++;
                moneda3=getDato(iren+2,col);
                if (moneda3.esPurpura()) contador++;
                moneda4=getDato(iren+3,col);
                if (moneda4.esPurpura()) contador++;
                if (contador==CUATRO) ganado=true;
                contador=0;
            }
        }
        return ganado;
    }
    
    /**
     * Determina si el jugador puede ganar al siguiente evento:
     * 1. banda
     * 2. barra
     * 3. faja
     * 4. palo
     * 
     * @return true si en uno de los ejes se pueden agrupar cuadro monedas iguales
     */
    public static Boolean PuedeGanar() {
        Boolean puede=false;
        int iren=0;
        int icol=0;
        Conectacuatrodata moneda1=new Conectacuatrodata();
        Conectacuatrodata moneda2=new Conectacuatrodata();
        Conectacuatrodata moneda3=new Conectacuatrodata();
        Conectacuatrodata moneda4=new Conectacuatrodata();
        
        //1.BANDA
        if (!puede) {
            Boolean tresjuntas=false;
            //buscando si hay 3 monedas juntas
            for (iren=0;iren<RENGLONES;iren++) {
                for (icol=0;icol<COLUMNAS;icol++) {
                    moneda1=getDato(iren+0,icol+0);
                    moneda2=getDato(iren+1,icol+1);
                    moneda3=getDato(iren+2,icol+2);
                    tresjuntas=moneda1.esPurpura() && 
                            moneda2.esPurpura() && 
                            moneda3.esPurpura();
                    if (tresjuntas) {
                        //si en esta columna encontramos tres,
                        //la más alta debe estar desocupada
                        if ((iren-1)>=0 && (icol-1)>=0) {
                            moneda4=getDato(iren-1,icol-1);
                            puede=moneda4.esBlanca();
                            if (puede) {
                                break;
                            }
                        }
                        if (!puede) {
                            if ((iren+3)<RENGLONES && (icol+3)<COLUMNAS) {
                                moneda4=getDato(iren+3,icol+3);
                                puede=moneda4.esBlanca();
                                if (puede) {
                                    break;
                                }
                            }
                        }
                        break; //while icol
                    }
                } //for icol
                if (puede) {
                    break;
                }
            } //for iren
        } //BANDA FIN
        
        //2.BARRA
        if (!puede) {
            Boolean tresjuntas=false;
            //buscando si hay 3 monedas juntas
            iren=RENGLONES;
            for (iren=0;iren<RENGLONES;iren++) {
                icol=COLUMNAS;
                while (icol>=0) {
                    moneda1=getDato(iren+0,icol-0);
                    moneda2=getDato(iren+1,icol-1);
                    moneda3=getDato(iren+2,icol-2);
                    tresjuntas=moneda1.esPurpura() && 
                            moneda2.esPurpura() && 
                            moneda3.esPurpura();
                    if (tresjuntas) {
                        //si en esta columna encontramos tres,
                        //la más alta debe estar desocupada
                        if ((iren-1)>=0 && (icol+1)>=0) {
                            moneda4=getDato(iren-1,icol+1);
                            puede=moneda4.esBlanca();
                            if (puede) {
                                break;
                            }
                        }
                        if (!puede) {
                            if ((iren+3)<RENGLONES && (icol-3)<COLUMNAS) {
                                moneda4=getDato(iren+3,icol-3);
                                puede=moneda4.esBlanca();
                                if (puede) {
                                    break;
                                }
                            }
                        }
                        break; //while icol
                    }
                    icol--;
                } //while icol
                if (puede) {
                    break;
                }
            } //for iren
        } //BARRA FIN
        
        //3.FAJA
        if (!puede) {
            Boolean tresjuntas=false;
            //buscando si hay 3 monedas juntas
            for (iren=0;iren<RENGLONES;iren++) {
                icol=0;
                tresjuntas=false;
                while (icol<COLUMNAS) {
                    moneda1=getDato(iren,icol+0);
                    moneda2=getDato(iren,icol+1);
                    moneda3=getDato(iren,icol+2);
                    tresjuntas=moneda1.esPurpura() && 
                            moneda2.esPurpura() && 
                            moneda3.esPurpura();
                    if (tresjuntas) {
                        break; //while icol
                    }
                    icol++;
                } //while icol
                if (tresjuntas) {
                    //si en esta columna encontramos tres,
                    //la más alta debe estar desocupada
                    if ((icol-1)>=0) {
                        moneda4=getDato(iren,icol-1);
                        puede=moneda4.esBlanca();
                        if (puede) {
                            break;
                        }
                    }
                    if (!puede) {
                        if ((icol+3)<COLUMNAS) {
                            moneda4=getDato(iren,icol+3);
                            puede=moneda4.esBlanca();
                            if (puede) {
                                break;
                            }
                        }
                    }
                }
            } //for
        } //FAJA
        //4.PALO
        if (!puede) {
            Boolean tresjuntas=false;
            //buscando si hay 3 monedas juntas
            for (icol=0;icol<COLUMNAS;icol++) {
                iren=0;
                tresjuntas=false;
                while (iren<RENGLONES) {
                    moneda1=getDato(iren+0,icol);
                    moneda2=getDato(iren+1,icol);
                    moneda3=getDato(iren+2,icol);
                    tresjuntas=moneda1.esPurpura() && 
                            moneda2.esPurpura() && 
                            moneda3.esPurpura();
                    if (tresjuntas) {
                        break; //while iren
                    }
                    iren++;
                }
                if (tresjuntas) {
                    //si en esta columna encontramos tres,
                    //la más alta debe estar desocupada
                    if ((iren-1)>=0) {
                        moneda4=getDato(iren-1,icol);
                        puede=moneda4.esBlanca();
                        if (puede) break; //icol
                    }
                }
            } //for
        } //PALO
        return puede;
    }

}
