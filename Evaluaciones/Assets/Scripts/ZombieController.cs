using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ZombieController : MonoBehaviour
{
    float velocity = 3;
    Rigidbody2D rb;
    Animator animator;
    SpriteRenderer sr;
    bool estado = true;
    GameManager gameManager;
    const int ANIMATION_QUIETO = 1;
    const int ANIMATION_CORRER = 0;

    void Start()
    {
        rb = GetComponent<Rigidbody2D>();
        sr = GetComponent<SpriteRenderer>();
        animator = GetComponent<Animator>();
        gameManager = FindObjectOfType<GameManager>();
    }


    void Update()
    {
        if(gameManager.Vidita() > 0)
        {
            rb.velocity = new Vector2(-velocity, rb.velocity.y);
            GirarAnimacion();
        }
        //if(gameManager.Vidita() <= 0)
        else
        Morir();
    }

    private void GirarAnimacion()
    {
        if(rb.velocity.x < 0)
        {
            sr.flipX = false;
        }
        else if(rb.velocity.x > 0)
        {
            sr.flipX = true;
        }
    }

    private void Morir()
    {
        Debug.Log("SE DETIENE ZOMBIE");
        estado = false;
        rb.velocity = new Vector2(0, rb.velocity.y);
        ChangeAnimation(ANIMATION_QUIETO);
    }

     private void OnCollisionEnter2D(Collision2D other) 
    {
        if(other.gameObject.name == "Kunai"){
            if(gameManager.Vidas()==0){
                Destroy(this.gameObject);
                Destroy(other.gameObject);
            }
        }
    } 
    private void ChangeAnimation(int animation)
    {
        animator.SetInteger("Estado", animation);
    }
}
