package domain.playMode;

import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import domain.abilities.*;
import domain.*;
import domain.database.*;
import domain.observers.*;
import domain.observers.PlayModeObserver.EventName;
import ui.playMode.TopBar;

public class Player extends GameObject {
    private static final int MAX_CHANCES = 4;
    private static final int MIN_CHANCES = 0;
    private static final int START_CHANCES = 3;
    private int chances;
    Noble noble;

    private boolean[] horizontalMovement;
    private boolean[] leftRightPressed = InputManager.leftRightPressed;
    private InputManager inputManager = InputManager.getInstance();
    private AbilityStorage abilityStorage;
    private ScoreManager scoreManager;
    private Map<Rectangle, AbilityTypes> abilityRectMap;
    private boolean isInit;

    public Player(Noble noble) {
        super();
        this.noble = noble;
        abilityStorage = AbilityStorage.getInstance();
        scoreManager = new ScoreManager();
        PlayModeObserver.getInstance().subscribe(EventName.LOAD_GAME,
                dObj -> setChances(((DatabaseObject) dObj).getChancesRemaining()));

    }

    @Override
    public void Start() {
        HandleMovement();
        setChances(START_CHANCES);
        var observer = PlayModeObserver.getInstance();
        observer.subscribe(EventName.CHANCE_DECREASED, o -> decreaseChance());
        observer.subscribe(EventName.CHANCE_INCREASED, o -> increaseChance());
        isInit = false;
    }

    @Override
    public void Update() {
        if (!isInit && TopBar.getAbilitiesRect() != null) {
            InitAbilities();
            isInit = true;
        }
        HandleMovement();
        HandleRotation();
        HandleAbilities();
        HandlePauseResume();

    }

    private void HandlePauseResume() {
        InputManager.getInstance().ListenKeyPressed(
                e -> PlayModeObserver.getInstance().notify(EventName.PAUSE_GAME, null), KeyEvent.VK_ESCAPE);
    }

    public void HandleMovement() {
        // MODIFIES: The noble direction into intended direction.
        // EFFECTS: If the right arrow key pressed, the noble moves to right,
        // if the left arrow key pressed, the noble moves to left,
        // if not valid key pressed or nothing pressed, the noble does not move.
        // REQUIRES: Valid key press (right arrow key, or left arrow key).

        horizontalMovement = inputManager.getLeftRightPressed();

        if (!horizontalMovement[0] && !horizontalMovement[1])
            noble.SetNobleDirection(0);
        else if (horizontalMovement[0])
            noble.SetNobleDirection(-1);
        else if (horizontalMovement[1])
            noble.SetNobleDirection(1);

    }

    private void HandleRotation() {
        var verticalMovement = inputManager.getUpDownPressed();

        if (!verticalMovement[0] && !verticalMovement[1])
            noble.SetNobleRotation(0);
        else if (verticalMovement[0])
            noble.SetNobleRotation(-1);
        else if (verticalMovement[1])
            noble.SetNobleRotation(1);
    }

    private void InitAbilities() {
        int abilitiesRectX = TopBar.getAbilitiesRect().x;
        int abilitiesRectY = TopBar.getAbilitiesRect().y;
        int abilitiesRectHeight = TopBar.getAbilitiesRect().height;

        abilityRectMap = Map.of(
                new Rectangle(abilitiesRectX, abilitiesRectY, 70, abilitiesRectHeight + 50),
                AbilityTypes.HEX,
                new Rectangle(abilitiesRectX + 70, abilitiesRectY, 70, abilitiesRectHeight + 50),
                AbilityTypes.UNSTOPPABLE,
                new Rectangle(abilitiesRectX + 140, abilitiesRectY, 70, abilitiesRectHeight + 50),
                AbilityTypes.EXPANSION

        );

        for (var name : abilityRectMap.keySet()) {
            String key = name.toString();
            String value = abilityRectMap.get(name).toString();
        }

        InputManager.getInstance().ListenKeyPressed(e -> abilityStorage.UseAbility(AbilityTypes.EXPANSION),
                KeyEvent.VK_T);
        InputManager.getInstance().ListenKeyPressed(e -> abilityStorage.UseAbility(AbilityTypes.HEX), KeyEvent.VK_H);
        InputManager.getInstance().ListenKeyPressed(e -> abilityStorage.UseAbility(AbilityTypes.UNSTOPPABLE),
                KeyEvent.VK_U);

        InputManager.getInstance().ListenMouseClick(this::HandleMouseClick);
    }

    private void HandleAbilities() {

        if (abilityStorage.isAcquired(AbilityTypes.INCREASECHANCE))
            abilityStorage.UseAbility(AbilityTypes.INCREASECHANCE);

    }

    private void HandleMouseClick(Point mousePos) {
        for (var set : abilityRectMap.entrySet()) {
            if (set.getKey().contains(mousePos))
                abilityStorage.UseAbility(set.getValue());
        }
    }

    public int getChances() {
        return chances;
    }

    public void setChances(int chances) {
        this.chances = chances;
        this.chances = Math.min(this.chances, MAX_CHANCES);
        this.chances = Math.max(this.chances, MIN_CHANCES);
        if (this.chances == 0) {
            PlayModeObserver.getInstance().notify(EventName.PLAYER_DEATH, null);
        }

        PlayModeObserver.getInstance().notify(EventName.UPDATE_CHANCES, this.chances);
    }

    public void increaseChance() {
        setChances(chances + 1);
    }

    public void decreaseChance() {
        setChances(chances - 1);
    }

    public int getScore() {
        return scoreManager.GetScore();
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    public void setHorizalMovement(boolean[] horizontalMovement) {
        this.horizontalMovement = horizontalMovement;
    }

}
