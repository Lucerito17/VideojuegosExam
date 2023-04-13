using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NinjaController : MonoBehaviour
{
    Rigidbody2D rb;
    Animator animator;
    SpriteRenderer sr;
    Collider2D cl;

    const int ANIMATION_QUIETO = 0;
    const int ANIMATION_CORRER = 1;
    const int ANIMATION_THROW = 2;
    const int ANIMATION_ATTACK = 3;
    const int ANIMATION_SLIDE = 4;
    const int ANIMATION_MORIR = 5;

    bool estado = true;
    bool aire = false;
    int velocity = 8;
    int velocitySlide = 2;
    float VelocityJump = 9;
    int cont = 0;

    void Start()
    {
        Debug.Log("Iniciando juego");
        rb = GetComponent<Rigidbody2D>();
        animator = GetComponent<Animator>();
        sr = GetComponent<SpriteRenderer>();
        cl = GetComponent<Collider2D>();
    }

    void Update()
    {
        if(estado == true)
        {
            Caminar();
            GirarAnimacion();
            Throw();
            Ataque();
            Deslizar();
            Saltar();
            SaltarDoble();
            CheckGround();
        }
    }
private void Caminar()
    {
        if(Input.GetKey(KeyCode.RightArrow))
        {
            rb.velocity = new Vector2(velocity, rb.velocity.y);
            ChangeAnimation(ANIMATION_CORRER);
        }
        else if(Input.GetKey(KeyCode.LeftArrow))
        {
            rb.velocity = new Vector2(-velocity, rb.velocity.y);
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
        if(Input.GetKey(KeyCode.C))
            ChangeAnimation(ANIMATION_ATTACK);
    }
    
    private void Deslizar()
    {
        if(Input.GetKey(KeyCode.RightArrow) && Input.GetKey(KeyCode.Z))
        {
            rb.velocity = new Vector2(velocitySlide, rb.velocity.y);
            ChangeAnimation(ANIMATION_SLIDE);
        }
        else if(Input.GetKey(KeyCode.LeftArrow) && Input.GetKey(KeyCode.Z))
        {
            rb.velocity = new Vector2(-velocitySlide, rb.velocity.y);
            ChangeAnimation(ANIMATION_SLIDE);
        }
    }

    private void Morir()
    {
        if(Input.GetKey(KeyCode.X))
        {
            estado = false;
            ChangeAnimation(ANIMATION_MORIR);
        }
            
    }

    private void Saltar()
    {
        animator.SetFloat("VelocityJump", rb.velocity.y);
        if(!cl.IsTouchingLayers(LayerMask.GetMask("Plataforma"))){ return;}
        if (Input.GetKeyDown(KeyCode.Space))
        {
            rb.velocity = new Vector2(rb.velocity.x, VelocityJump);
        }
    }

    private void SaltarDoble()
    {
        if(aire)
        {
            Debug.Log(cont);
            if(!cl.IsTouchingLayers(LayerMask.GetMask("Plataforma")))
            {
                if (Input.GetKeyDown(KeyCode.V)&&cont>0)
                {   
                    rb.velocity = new Vector2(rb.velocity.x, VelocityJump+4);
                    cont--;
                }
            }
        }
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if(other.gameObject.tag=="Saltar")
        {
            cont++;
            Debug.Log("SE FUE");
            Destroy(other.gameObject);
        }
    }

    private void CheckGround()
    {
        if(cl.IsTouchingLayers(LayerMask.GetMask("Plataforma")))
        {
            animator.SetBool("isGround", true);
        }
        else
        {
            animator.SetBool("isGround", false);
            aire = true;
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
