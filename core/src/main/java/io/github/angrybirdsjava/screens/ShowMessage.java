package io.github.angrybirdsjava.screens;

import com.badlogic.gdx.math.Vector2;

public class ShowMessage {
    public String message;
    public float duration; // How long to display the message
    public float timer; // Timer to track the remaining time
    public Vector2 position; // Position where the message will be displayed
    public String type;

    public ShowMessage(String type,String message, float x, float y, float duration) {
        this.type = type;
        this.message = message;
        this.position = new Vector2(x, y);
        this.duration = duration;
        this.timer = duration;
    }

    public void update(float deltaTime) {

        if (timer > 0) {
            timer -= deltaTime;
        } else {
            message = null; // Message is no longer needed
        }
    }

    public boolean isFinished() {
        return message == null; // Finished when message is null
    }
}

