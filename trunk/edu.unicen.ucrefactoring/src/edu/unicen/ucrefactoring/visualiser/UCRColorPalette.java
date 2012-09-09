package edu.unicen.ucrefactoring.visualiser;

import org.eclipse.contribution.visualiser.palettes.DefaultVisualiserPalette;
import org.eclipse.swt.graphics.RGB;

/**
 * Custom color palette to represent refactoring score
 * @author migue
 *
 */
public class UCRColorPalette  extends  DefaultVisualiserPalette{

    protected static RGB[] rgbList = new RGB[] { 
	    new RGB(255, 0, 0),
	    new RGB(255, 60, 0),
	    new RGB(255, 120, 0),
	    new RGB(255, 180, 0),
	    new RGB(255, 255, 0),
	    new RGB(230, 230, 80),
	    new RGB(200, 200, 120),
	    new RGB(170, 170, 140),
	    new RGB(120, 120, 110),
	    new RGB(50, 50, 50),
	    new RGB(0, 0, 0)
    };
	  
	@Override
	public RGB[] getRGBValues() {
	    return rgbList;
	}



}
