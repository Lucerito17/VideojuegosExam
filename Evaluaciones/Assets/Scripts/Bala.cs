using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Bala : MonoBehaviour
{
    public float velocity = 15;
    Rigidbody2D rb;
    SpriteRenderer sr;
    float realVelocity;
    public GameObject balitas;

    public void SetRightDirection()
    {
        realVelocity = velocity;
    }

    public void SetLeftDirection()
    {
        realVelocity = -velocity;
    }

    void Start()
    {
        rb = GetComponent<Rigidbody2D>();
        sr = GetComponent<SpriteRenderer>();
        Destroy(this.gameObject, 3);//eliminacion del objeto
    }

    void Update()
    {
        rb.velocity = new Vector2(realVelocity, 0); 
        if(Input.GetKeyDown(KeyCode.G))
        {
            CrearBalaDown();
            CrearBalaUp();
        }
    }

    void CrearBalaUp()
    {
        var BalasPosition = transform.position + new Vector3(0,2,0);
        var gb = Instantiate(balitas, BalasPosition, Quaternion.identity) as GameObject;
        
        var controller = gb.GetComponent<Balas>();
        controller.SetUpDirection(velocity);
    }

    void CrearBalaDown()
    {
        var BalasPosition = transform.position + new Vector3(0,-2,0);
        var gb = Instantiate(balitas, BalasPosition, Quaternion.identity) as GameObject;
        
        var controller = gb.GetComponent<Balas>();
        controller.SetDownDirection(velocity);
    }

    public void OnCollisionEnter2D(Collision2D other)//para chocar y eliminar
    {   
        
        if (other.gameObject.tag == "Enemy")
        { 
            Destroy(this.gameObject);//se topa con el objeto 
            Destroy(other.gameObject);//destruye al objeto topado
        }
    }
}
