package ui.painters;

import java.util.ArrayList;
import domain.editMode.EditMode;

public class EditModePainter {

    private EditMode editMode;

    public EditModePainter(EditMode editMode) {
        this.editMode = editMode;
    }

    public ArrayList<IGameObjectPainter> InitPainters() {
        var painters = new ArrayList<IGameObjectPainter>();

       
        painters.add(new ObstaclesPainter(editMode.getObstacles()));

        return painters; 
    }
    
}
