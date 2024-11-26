package io.github.angrybirdsjava.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.*;
import io.github.angrybirdsjava.birds.Black_Bird;
import io.github.angrybirdsjava.birds.Blue_Bird;
import io.github.angrybirdsjava.birds.Red_Bird;
import io.github.angrybirdsjava.birds.Yellow_Bird;
import io.github.angrybirdsjava.blocks.Structures;

import java.util.ArrayList;

public class ContactDetect implements ContactListener {
    public boolean birdCollided = false;

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        // Check if the bird collided with a relevant object
        if (isBirdFixture(fixtureA,fixtureB)) {
            System.out.println("hellowww");
            birdCollided = true;
        } else if (isBirdFixture(fixtureB,fixtureA)) {
            System.out.println("hellowww");
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

    private boolean isBirdFixture(Fixture fixtureA,Fixture fixtureB) {
        boolean b=fixtureA.getUserData() != null && ((ArrayList)(fixtureA.getUserData())).get(0).equals("bird");
        if (fixtureB.getUserData() != null && fixtureA.getUserData()!=null) {
            ArrayList block=(ArrayList)(fixtureB.getUserData());
            ArrayList bird=(ArrayList)(fixtureA.getUserData());
            String typebird="n";
            if (bird.size()>=2 && bird.get(1) instanceof Black_Bird){
                typebird="Black";
            }else if (bird.size()>=2 && bird.get(1) instanceof Red_Bird){
                typebird="Red";
            }
            else if (bird.size()>=2 && bird.get(1) instanceof Yellow_Bird){
                typebird="Yellow";
            }
            else if (bird.size()>=2 && bird.get(1) instanceof Blue_Bird){
                typebird="Blue";
            }
            if (!typebird.equals("n") && block.get(0) instanceof Structures){
                Structures s=(Structures)block.get(0);
                if (typebird.equals("Black")){
                    Black_Bird birdclass=(Black_Bird) (((ArrayList)fixtureA.getUserData()).get(1));
                    s.applyDamage(birdclass.damage,fixtureB);
                }else if (typebird.equals("Red")){
                    Red_Bird birdclass=(Red_Bird) (((ArrayList)fixtureA.getUserData()).get(1));
                    s.applyDamage(birdclass.damage,fixtureB);
                }else if (typebird.equals("Yellow")){
                    Yellow_Bird birdclass=(Yellow_Bird) (((ArrayList)fixtureA.getUserData()).get(1));
                    s.applyDamage(birdclass.damage,fixtureB);
                }else if (typebird.equals("blue")){
                    Blue_Bird birdclass=(Blue_Bird) (((ArrayList)fixtureA.getUserData()).get(1));
                    s.applyDamage(birdclass.damage,fixtureB);
                }

            }
        }
        return b;
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
