using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;
using UnityEngine.UI;

public class PersonajeController : MonoBehaviour
{
    Rigidbody2D rb;
    SpriteRenderer sr;
    Animator animator;
    Collider2D cl;

    private float velocity = 5;
    private float jumpVelocity = 6;

    const int ANIMATION_QUIETO = 0;
    const int ANIMATION_CAMINAR = 1;
    const int ANIMATION_SALTAR = 2;

    void Start()
    {
        rb = GetComponent<Rigidbody2D>();
        sr = GetComponent<SpriteRenderer>();
        animator = GetComponent<Animator>();
        cl = GetComponent<Collider2D>();
    }

    void Update()
    {
        Caminar();
        GirarAnimacion();
        CheckGround();
        Saltar();
    }

    //CAMBIAR ANIMACION
    private void ChangeAnimation(int animation)
    {
        animator.SetInteger("Estado", animation);
    }

    //CAMINAR
    private void Caminar()
    {
        if(Input.GetKey(KeyCode.RightArrow))
        {
            rb.velocity = new Vector2(velocity, rb.velocity.y);
            ChangeAnimation(ANIMATION_CAMINAR);
        }
        else if(Input.GetKey(KeyCode.LeftArrow))
        {
            rb.velocity = new Vector2(-velocity, rb.velocity.y);
            ChangeAnimation(ANIMATION_CAMINAR);
        }
        else
        {
            rb.velocity = new Vector2(0, rb.velocity.y);
            ChangeAnimation(ANIMATION_QUIETO);
        }
    }

    //GIRAR AL PERSONAJE
    private void GirarAnimacion()
    {
        if(rb.velocity.x < 0)
        {
            sr.flipX = true;
        }
        else if(rb.velocity.x > 0)
        {
            sr.flipX = false;
        }
    }

    //SALTAR
    private void Saltar()
    {
        animator.SetFloat("jumpVelocity", rb.velocity.y);
        if(!cl.IsTouchingLayers(LayerMask.GetMask("Suelo"))){return;}
        if (Input.GetKeyUp(KeyCode.Space))
        {
            rb.velocity = new Vector2(rb.velocity.x, jumpVelocity);
            
        }
    }
    
    private void CheckGround()
    {
        if(cl.IsTouchingLayers(LayerMask.GetMask("Suelo")))
        {
            animator.SetBool("isGround", true);
        }
        else
        {
            animator.SetBool("isGround", false);
        }
    }
}
