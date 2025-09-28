using UnityEngine;
using System.Collections;

public class Dialog : MonoBehaviour {
    void OnCollisionEnter(Collision myCollision) {
        if (myCollision.gameObject.name == "Wall") {
            Debug.Log("Wall was hitted by kube");
        }
        else if (myCollision.gameObject.name == "Cube") {
            Debug.Log("Kube hit Wall");
        }

        Destroy(gameObject);
    }
} 