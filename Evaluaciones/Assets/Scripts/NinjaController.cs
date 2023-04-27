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
    public GameObject balita;
    public AudioClip jumpSound;
    public AudioClip bulletSound;
    public AudioClip coinSound;
    public AudioClip deadSound;
    GameManager gameManager;
    AudioSource audioSource;

    bool estado = true;
    bool aire = false;
    int velocity = 8;
    int velocitySlide = 2;
    float VelocityJump = 10;
    int cont = 0;

    void Start()
    {
        Debug.Log("Iniciando juego");
        rb = GetComponent<Rigidbody2D>();
        animator = GetComponent<Animator>();
        sr = GetComponent<SpriteRenderer>();
        cl = GetComponent<Collider2D>();
        gameManager = FindObjectOfType<GameManager>();
        audioSource = GetComponent<AudioSource>(); 
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
            Disparar();
            //SaltarDoble();
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

    private void Disparar()
    {
        if(gameManager.Balas() > 0)
        {
            if(Input.GetKeyUp(KeyCode.F))
            {
                Bala(3,0,0);
                gameManager.MenosBalas(1);
                audioSource.PlayOneShot(bulletSound);
            }
        }
    }

    public void Bala(int posX, int posY, int posZ)
    {
        if(sr.flipX==true){//disparar hacia la izquierda  
                var BalasPosition = transform.position + new Vector3(-posX,posY,posZ);//negativo
                var gb = Instantiate(balita, BalasPosition, Quaternion.identity) as GameObject;
                //llamar bala, posicion bala , direcion bala
                var controller = gb.GetComponent<Bala>();
                controller.SetLeftDirection();
            }
        if(sr.flipX==false){//disparar hacia la derecha
                var BalasPosition = transform.position + new Vector3(posX,posY,posZ);//positivo
                var gb = Instantiate(balita, BalasPosition, Quaternion.identity) as GameObject;
                //llamar bala, posicion bala , direcion bala
                var controller = gb.GetComponent<Bala>();
                controller.SetRightDirection();
            }
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
        estado = false;
        ChangeAnimation(ANIMATION_MORIR);
        audioSource.PlayOneShot(deadSound);
    }

    private void Saltar()
    {
        animator.SetFloat("VelocityJump", rb.velocity.y);
        if(!cl.IsTouchingLayers(LayerMask.GetMask("Plataforma"))){ return;}
        if (Input.GetKeyDown(KeyCode.Space))
        {
            rb.velocity = new Vector2(rb.velocity.x, VelocityJump);
            audioSource.PlayOneShot(jumpSound);
        }
    }

    private void SaltarDoble()
    {
        if(aire)
        {
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
    private void OnCollisionEnter2D(Collision2D other) 
    {
        if(other.gameObject.tag == "Enemy")
        {
            gameManager.RestaVida();
            if(gameManager.Vidita() == 0)
            {
                Debug.Log("Parar personaje");
                Morir();
            }
        }
    } 

    private void OnTriggerEnter2D(Collider2D other)
    {
        if(other.gameObject.tag=="Enemy")
        {
            Debug.Log("SE FUE");
            Destroy(other.gameObject);
        }

        /*if(other.gameObject.tag == "salto1");
        {
            velocity = velocity + 3;
            Debug.Log(velocity);
        }*/

        if(other.gameObject.tag == "Coin");
        {
            Destroy(other.gameObject);
            gameManager.SumaMonedas();
            audioSource.PlayOneShot(coinSound);
        }
        if(other.gameObject.tag == "mas");
        {
            Destroy(other.gameObject);
            gameManager.MasBalas(5);
            audioSource.PlayOneShot(coinSound);
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
