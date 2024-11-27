package io.github.angrybirdsjava.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.*;
import io.github.angrybirdsjava.birds.Black_Bird;
import io.github.angrybirdsjava.birds.Blue_Bird;
import io.github.angrybirdsjava.birds.Red_Bird;
import io.github.angrybirdsjava.birds.Yellow_Bird;
import io.github.angrybirdsjava.blocks.Structures;
import io.github.angrybirdsjava.pigs.Crown_Pig;
import io.github.angrybirdsjava.pigs.Mucchad_Pig;
import io.github.angrybirdsjava.pigs.Normal_Pig;

import java.util.ArrayList;

public class ContactDetect implements ContactListener {
    public boolean birdCollided = false;

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        managedamage(fixtureA, fixtureB);
        if (isBirdFixture(fixtureA,fixtureB)) {
            birdCollided = true;
        } else if (isBirdFixture(fixtureB,fixtureA)) {
            birdCollided = true;
        }
    }
    public String typecheck(ArrayList a) {
        if (a.size()>=1 && a.get(0) instanceof Structures) {
            return "structure";
        }else if (a.size()>=2 && a.get(1) instanceof Black_Bird){
            return "Black";
        }else if (a.size()>=2 && a.get(1) instanceof Red_Bird){
            return "Red";
        }
        else if (a.size()>=2 && a.get(1) instanceof Yellow_Bird){
            return  "Yellow";
        }
        else if (a.size()>=2 && a.get(1) instanceof Blue_Bird){
            return  "Blue";
        }else if (a.size()>=1 && a.get(0) instanceof Crown_Pig){
            return  "Crownpig";
        }else if (a.size()>=1 && a.get(0) instanceof Mucchad_Pig){
            return  "Mucchadpig";
        }else if (a.size()>=1 && a.get(0) instanceof Normal_Pig){
            return  "Normalpig";
        }
        else if (a.size()>=1 && ((String)(a.get(0))).equals("ground")){
            return  "ground";
        }else{
            return "sling";
        }
    }

    public void managedamage(Fixture fixtureA, Fixture fixtureB) {
        if (fixtureB.getUserData() != null && fixtureA.getUserData()!=null) {
            ArrayList arr1=(ArrayList)(fixtureA.getUserData());
            ArrayList arr2=(ArrayList)(fixtureB.getUserData());
            String type1="null";
            String type2="null";
            type1=typecheck(arr1);
            type2=typecheck(arr2);
            if (type1.equals("sling") || type1.equals("sling")) return;

            if (type2.equals("structure")) {
                Structures s=(Structures)arr2.get(0);
                if (type1.equals("Black")){
                    Black_Bird birdclass=(Black_Bird) (arr1.get(1));
                    s.applyDamage(birdclass.damage,fixtureB);
                }else if (type1.equals("Red")){
                    Red_Bird birdclass=(Red_Bird) (arr1.get(1));
                    s.applyDamage(birdclass.damage,fixtureB);
                }else if (type1.equals("Yellow")){
                    Yellow_Bird birdclass=(Yellow_Bird) (arr1.get(1));
                    s.applyDamage(birdclass.damage,fixtureB);
                }else if (type1.equals("Blue")){
                    Blue_Bird birdclass=(Blue_Bird) (arr1.get(1));
                    s.applyDamage(birdclass.damage,fixtureB);
                }else if (type1.equals("Crownpig")){
                    Crown_Pig pig=(Crown_Pig) (arr1.get(0));
                    pig.applyDamage(fixtureA,s);
                }else if (type1.equals("Mucchadpig")){
                    Mucchad_Pig pig=(Mucchad_Pig) (arr1.get(0));
                    pig.applyDamage(fixtureA,s);
                }else if (type1.equals("Normalpig")){
                    Normal_Pig pig=(Normal_Pig) (arr1.get(0));
                    pig.applyDamage(fixtureA,s);
                }else if (type1.equals("ground")){
                    s.applyDamage(0.1f,fixtureB);
                }else if (type1.equals("structure")){
                    Structures structures=(Structures)arr1.get(0);
                    s.applyDamage(structures.intercollsion,fixtureB);
                    structures.applyDamage(s.intercollsion,fixtureA);
                }
            }
            else if (type1.equals("structure")) {
                Structures s=(Structures)arr1.get(0);
                if (type2.equals("Black")){
                    Black_Bird birdclass=(Black_Bird) (arr2.get(1));
                    s.applyDamage(birdclass.damage,fixtureA);
                }else if (type2.equals("Red")){
                    Red_Bird birdclass=(Red_Bird) (arr2.get(1));
                    s.applyDamage(birdclass.damage,fixtureA);
                }else if (type2.equals("Yellow")){
                    Yellow_Bird birdclass=(Yellow_Bird) (arr2.get(1));
                    s.applyDamage(birdclass.damage,fixtureA);
                }else if (type2.equals("Blue")){
                    Blue_Bird birdclass=(Blue_Bird) (arr2.get(1));
                    s.applyDamage(birdclass.damage,fixtureA);
                }else if (type2.equals("Crownpig")){
                    Crown_Pig pig=(Crown_Pig) (arr2.get(0));
                    pig.applyDamage(fixtureB,s);
                }else if (type2.equals("Mucchadpig")){
                    Mucchad_Pig pig=(Mucchad_Pig) (arr2.get(0));
                    pig.applyDamage(fixtureB,s);
                }else if (type2.equals("Normalpig")){
                    Normal_Pig pig=(Normal_Pig) (arr2.get(0));
                    pig.applyDamage(fixtureB,s);
                }else if (type2.equals("ground")){
                    s.applyDamage(0.1f,fixtureA);
                }
            }
            else if (type1.equals("Crownpig")) {
                System.out.println("hellowowww");
                Crown_Pig pig=(Crown_Pig) arr1.get(0);
                if (type2.equals("Black")){
                    Black_Bird birdclass=(Black_Bird) (arr2.get(1));
                    pig.applyDamage(fixtureA,3f);
                }else if (type2.equals("Red")){
                    Red_Bird birdclass=(Red_Bird) (arr2.get(1));
                    pig.applyDamage(fixtureA,2.5f);
                }else if (type2.equals("Yellow")){
                    Yellow_Bird birdclass=(Yellow_Bird) (arr2.get(1));
                    pig.applyDamage(fixtureA,2.5f);
                }else if (type2.equals("Blue")){
                    Blue_Bird birdclass=(Blue_Bird) (arr2.get(1));
                    pig.applyDamage(fixtureA,2f);
                }else if (type2.equals("ground")){
                    pig.applyDamage(fixtureA,1f);
                }
            }
            else if (type2.equals("Crownpig")) {
                Crown_Pig pig=(Crown_Pig) arr2.get(0);
                if (type1.equals("Black")){
                    Black_Bird birdclass=(Black_Bird) (arr1.get(1));
                    pig.applyDamage(fixtureB,3f);
                }else if (type1.equals("Red")){
                    Red_Bird birdclass=(Red_Bird) (arr1.get(1));
                    pig.applyDamage(fixtureB,2.5f);
                }else if (type1.equals("Yellow")){
                    Yellow_Bird birdclass=(Yellow_Bird) (arr1.get(1));
                    pig.applyDamage(fixtureB,2.5f);
                }else if (type1.equals("Blue")){
                    Blue_Bird birdclass=(Blue_Bird) (arr1.get(1));
                    pig.applyDamage(fixtureB,2f);
                }else if (type1.equals("ground")){
                    pig.applyDamage(fixtureB,1f);
                }
            }
            else if (type1.equals("Normalpig")) {
                Normal_Pig pig=(Normal_Pig) arr1.get(0);
                if (type2.equals("Black")){
                    Black_Bird birdclass=(Black_Bird) (arr2.get(1));
                    pig.applyDamage(fixtureA,3f);
                }else if (type2.equals("Red")){
                    Red_Bird birdclass=(Red_Bird) (arr2.get(1));
                    pig.applyDamage(fixtureA,2.5f);
                }else if (type2.equals("Yellow")){
                    Yellow_Bird birdclass=(Yellow_Bird) (arr2.get(1));
                    pig.applyDamage(fixtureA,2.5f);
                }else if (type2.equals("Blue")){
                    Blue_Bird birdclass=(Blue_Bird) (arr2.get(1));
                    pig.applyDamage(fixtureA,2f);
                }else if (type2.equals("ground")){
                    pig.applyDamage(fixtureA,1f);
                }
            }
            else if (type2.equals("Normalpig")) {
                Normal_Pig pig=(Normal_Pig) arr2.get(0);
                if (type1.equals("Black")){
                    Black_Bird birdclass=(Black_Bird) (arr1.get(1));
                    pig.applyDamage(fixtureB,3f);
                }else if (type1.equals("Red")){
                    Red_Bird birdclass=(Red_Bird) (arr1.get(1));
                    pig.applyDamage(fixtureB,2.5f);
                }else if (type1.equals("Yellow")){
                    Yellow_Bird birdclass=(Yellow_Bird) (arr1.get(1));
                    pig.applyDamage(fixtureB,2.5f);
                }else if (type1.equals("Blue")){
                    Blue_Bird birdclass=(Blue_Bird) (arr1.get(1));
                    pig.applyDamage(fixtureB,2f);
                }else if (type1.equals("ground")){
                    pig.applyDamage(fixtureB,1f);
                }
            }
            else if (type1.equals("Mucchadpig")) {
                Mucchad_Pig pig=(Mucchad_Pig) arr1.get(0);
                if (type2.equals("Black")){
                    Black_Bird birdclass=(Black_Bird) (arr2.get(1));
                    pig.applyDamage(fixtureA,3f);
                }else if (type2.equals("Red")){
                    Red_Bird birdclass=(Red_Bird) (arr2.get(1));
                    pig.applyDamage(fixtureA,2.5f);
                }else if (type2.equals("Yellow")){
                    Yellow_Bird birdclass=(Yellow_Bird) (arr2.get(1));
                    pig.applyDamage(fixtureA,2.5f);
                }else if (type2.equals("Blue")){
                    Blue_Bird birdclass=(Blue_Bird) (arr2.get(1));
                    pig.applyDamage(fixtureA,2f);
                }else if (type2.equals("ground")){
                    pig.applyDamage(fixtureA,1f);
                }
            }
            else if (type2.equals("Mucchadpig")) {
                Mucchad_Pig pig=(Mucchad_Pig) arr2.get(0);
                if (type1.equals("Black")){
                    Black_Bird birdclass=(Black_Bird) (arr1.get(1));
                    pig.applyDamage(fixtureB,3f);
                }else if (type1.equals("Red")){
                    Red_Bird birdclass=(Red_Bird) (arr1.get(1));
                    pig.applyDamage(fixtureB,2.5f);
                }else if (type1.equals("Yellow")){
                    Yellow_Bird birdclass=(Yellow_Bird) (arr1.get(1));
                    pig.applyDamage(fixtureB,2.5f);
                }else if (type1.equals("Blue")){
                    Blue_Bird birdclass=(Blue_Bird) (arr1.get(1));
                    pig.applyDamage(fixtureB,2f);
                }else if (type1.equals("ground")){
                    pig.applyDamage(fixtureB,1f);
                }
            }
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
        return fixtureA.getUserData() != null && ((ArrayList)(fixtureA.getUserData())).get(0).equals("bird");

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
