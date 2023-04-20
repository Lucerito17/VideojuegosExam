using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Spawn : MonoBehaviour
{

    public GameObject zombie;
    float cont = 0;
    void Start()
    {
        
    }

    void Update()
    {
        Debug.Log(Time.deltaTime);
        cont += Time.deltaTime;
        if(cont > 3)
        {
            GenerarZombie();
            cont = 0;
        }
    }

    private void GenerarZombie()
    {
        var ZombiePosition = transform.position + new Vector3(-3,0,0);
        var gb = Instantiate(zombie, ZombiePosition, Quaternion.identity) as GameObject;
        var controller = gb.GetComponent<ZombieController>();
    }
}
