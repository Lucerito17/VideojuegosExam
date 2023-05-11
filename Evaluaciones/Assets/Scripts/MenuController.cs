using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class MenuController : MonoBehaviour
{
    public GameObject PersonajeH;
    public GameObject Player;
    bool cambio = true;
    void Start()
    {
        
    }
    public void StartGame(){
        if(cambio==true)
        {
            SceneManager.LoadScene(1);
        }
        else
        {
            SceneManager.LoadScene(3);
        }
    }

    public void CambioPersonaje()
    {
        if(cambio==false)
        {
            GameObject aux;
            aux = GameObject.Find("PersonajeH(Clone)");
            Destroy(aux.gameObject);
            var PlayerPosition = new Vector3(0.21f,0.74f,0f);
            var gb = Instantiate(Player, PlayerPosition, Quaternion.identity) as GameObject;
        }
        else
        {
            GameObject aux;
            aux = GameObject.Find("Player(Clone)");
            Destroy(aux.gameObject);
            var PlayerPosition = new Vector3(0.21f,0.74f,0f);
            var gb = Instantiate(PersonajeH, PlayerPosition, Quaternion.identity) as GameObject;
        }
    }

    public void CambioDerecha()
    {
        cambio=! cambio;
        CambioPersonaje();
    }
}
