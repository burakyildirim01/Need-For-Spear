package domain.abilities;

import domain.*;
import domain.playMode.*;
import java.awt.*;

public class AbilityBox extends GameObject implements INobleCollision {
  private int width = 20;
  private int height = 20;
  private int x;
  private int y;

  protected boolean isVisible = false;
  private Ability ability;

  private double speed = 6 * 35 / 4 * GameLoop.DELTA_TIME;
  private AbilityTypes abilityType;

  public AbilityBox(int x, int y, AbilityTypes abilityType) {
    this.abilityType = abilityType;
    this.x = x + 5;
    this.y = y + 5;


    CollisionManager.getInstance().addCollider((INobleCollision) this);
  }

  @Override
  public void Update() {
    if (isVisible) {
      y += Math.ceil(speed);
    }
  }

  @Override
  public void collide(Noble noble) {

    AbilityStorage.getInstance().AbilityAcquired(AbilityManager.getInstance().getAbility(abilityType));

    CollisionManager.getInstance().removeCollider((INobleCollision) this);

    isVisible = false;

    Destroy();

  }

  @Override
  public boolean isCollide(Noble noble) {
    return noble.getBounds().intersects(getBounds());
  }

  public Rectangle getBounds() {
    return new Rectangle(x, y, width, height);
  }

  public void setVisible(boolean isVisible) {
    this.isVisible = isVisible;
  }

  public boolean getVisible() {
    return isVisible;
  }

  public Ability getAbility() {
    return ability;
  }

  public AbilityTypes getAbilityType() {
    return abilityType;
  }


}
