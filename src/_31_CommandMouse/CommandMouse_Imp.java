package _31_CommandMouse;
import java.awt.Point;

public interface CommandMouse_Imp
{
	public void pressed(Point point);
	public void dragged(Point point);
	public void clicked(Point point);
}
