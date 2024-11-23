package io.github.angrybirdsjava.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.*;

public class ContactDetect implements ContactListener {
    public boolean birdCollided = false;

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        // Check if the bird collided with a relevant object
        if (isBirdFixture(fixtureA)) {
            birdCollided = true;
        } else if (isBirdFixture(fixtureB)) {
            birdCollided = true;
        }
    }

    @Override
    public void endContact(Contact contact) {
        // Not used for this logic
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}

    private boolean isBirdFixture(Fixture fixture) {
        return fixture.getUserData() != null && fixture.getUserData().equals("bird");
    }

//    private boolean isRelevantCollision(Fixture fixture) {
//        // Check if this fixture belongs to a pig or important obstacle
//        Object userData = fixture.getUserData();
//        return userData != null && (userData.equals("pig") || userData.equals("obstacle"));
//    }

    public boolean hasBirdCollided() {
        return birdCollided;
    }

    public void resetBirdCollision() {
        birdCollided = false;
    }
}
