using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NinjaController : MonoBehaviour
{
    Rigidbody2D rb;
    Animator animator;
    SpriteRenderer sr;

    const int ANIMATION_QUIETO = 0;
    const int ANIMATION_CORRER = 1;
    const int ANIMATION_THROW = 2;
    const int ANIMATION_ATTACK = 3;
    const int ANIMATION_SLIDE = 4;
    const int ANIMATION_GLIDE = 5;
    const int ANIMATION_MORIR = 6;

    bool estado = true;
    // Start is called before the first frame update
    void Start()
    {
        Debug.Log("Iniciando juego");
        rb = GetComponent<Rigidbody2D>();
        animator = GetComponent<Animator>();
        sr = GetComponent<SpriteRenderer>();
    }

    // Update is called once per frame
    void Update()
    {
        if(estado == true)
        {
            Caminar();
            GirarAnimacion();
            Throw();
            Ataque();
            Deslizar();
            Glide();
        }
    }
private void Caminar()
    {
        if(Input.GetKey(KeyCode.RightArrow))
        {
            rb.velocity = new Vector2(3, rb.velocity.y);
            ChangeAnimation(ANIMATION_CORRER);
        }
        else if(Input.GetKey(KeyCode.LeftArrow))
        {
            rb.velocity = new Vector2(-3, rb.velocity.y);
            ChangeAnimation(ANIMATION_CORRER);
        }
        else 
        {
            rb.velocity = new Vector2(0, rb.velocity.y);
            ChangeAnimation(ANIMATION_QUIETO);
        }
        Morir();
    }

    private void Throw()
    {
        if(Input.GetKey(KeyCode.B))
            ChangeAnimation(ANIMATION_THROW);
    }

    private void Ataque()
    {
        if(Input.GetKey(KeyCode.X))
            ChangeAnimation(ANIMATION_ATTACK);
    }
    
    private void Deslizar()
    {
        if(Input.GetKey(KeyCode.RightArrow) && Input.GetKey(KeyCode.Z))
        {
            rb.velocity = new Vector2(4, rb.velocity.y);
            ChangeAnimation(ANIMATION_SLIDE);
        }
        else if(Input.GetKey(KeyCode.LeftArrow) && Input.GetKey(KeyCode.Z))
        {
            rb.velocity = new Vector2(-4, rb.velocity.y);
            ChangeAnimation(ANIMATION_SLIDE);
        }
    }

    private void Glide()
    {
        if(Input.GetKey(KeyCode.P))
            ChangeAnimation(ANIMATION_GLIDE);
    }

    private void Morir()
    {
        if(Input.GetKey(KeyCode.K))
        {
            estado = false;
            ChangeAnimation(ANIMATION_MORIR);
        }
            
    }

    private void ChangeAnimation(int animation)
    {
        animator.SetInteger("Estado", animation);
    }

    private void GirarAnimacion()
    {
        if(rb.velocity.x < 0)
        {
            sr.flipX = true;
        }
        else if (rb.velocity.x > 0)
        {
            sr.flipX = false;
        }
    }
}
