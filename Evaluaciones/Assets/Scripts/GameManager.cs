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
    int cont;
    int vidita;
    int balas;
    void Start()
    {
        cont = 0;
        vidita = 3;
        balas = 5;
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
    public void TextVista()
    {
        MonedasTxt.text = "Monedas : " + cont;
        VidaTxt.text ="Vida : " + vidita;
        BalasTxt.text ="Balas : 5 / " + balas;
    }
}
