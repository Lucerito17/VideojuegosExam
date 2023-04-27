using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using TMPro;

public class GameManager : MonoBehaviour
{
    public TMP_Text MonedasTxt ;
    public TMP_Text VidaTxt ;
    public TMP_Text BalasTxt;
    public TMP_Text ZombieTxt;
    int cont;
    int vidita;
    int balas;
    int cant;
    public int vidas;
    void Start()
    {
        cont = 0;
        vidita = 2;
        balas = 5;
        cant = 0;
        vidas = 2;
        TextVista();
    }

    public int Cont()
    {
        return cont;
    }
    public int Vidita()
    {
        return vidita;
    }
    public int Balas()
    {
        return balas;
    }
    public int Cantidad()
    {
        return cant;
    }
    public int Vidas()
    {
        return vidas;
    }

    public void SumaMonedas()
    {
        cont++;
        TextVista();
    }

    public void RestaVida()
    {
        vidita--;
        TextVista();
    }

    public void MenosBalas(int resta)
    {
        balas-=resta;
        TextVista();
    }

    public void MasBalas(int suma)
    {
        balas += suma;
        TextVista();
    }

    public void CantZombie(int canti)
    {
        cant++;
        TextVista();
    }

    public void RestaVidaZombie(int menos)
    {
        vidas-=menos;
    }
    public void TextVista()
    {
        MonedasTxt.text = "Monedas : " + cont;
        VidaTxt.text ="Vida : " + vidita;
        BalasTxt.text ="Balas : " + balas;
        ZombieTxt.text="Zombies : " + cant;
    }
}
