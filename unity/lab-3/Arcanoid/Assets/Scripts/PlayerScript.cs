using UnityEngine;

public class PlayerScript : MonoBehaviour 
{
    public float playerVelocity;
    private Vector3 playerPosition;
    public float boundary;

    void Start () {
        playerPosition = gameObject.transform.position;
    }

    void Update() {
        playerPosition.x += Input.GetAxis("Horizontal") * playerVelocity;
 
        if (Input.GetKeyDown(KeyCode.Escape)){
            Application.Quit();
        }
 
        transform.position = playerPosition;

        if (playerPosition.x < -boundary) {
            transform.position = new Vector3 (-boundary, playerPosition.y, playerPosition.z);
        } 
        if (playerPosition.x > boundary) {
            transform.position = new Vector3(boundary, playerPosition.y, playerPosition.z);     
        }
    }
}
