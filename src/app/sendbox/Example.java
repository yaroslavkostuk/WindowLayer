package app.sendbox;

import app.events.Dispatcher;
import app.events.Event;
import app.events.types.MouseMotionEvent;
import app.events.types.MousePressedEvent;
import app.events.types.MouseReleasedEvent;
import app.layers.Layer;

import java.awt.*;
import java.util.Random;

public class Example extends Layer {

    private String name;
    private Color color;
    private Rectangle rect;
    private boolean dragging = false;
    private int px, py;

    private static final Random random = new Random();

    public Example(String name, Color color){
        this.name = name;
        this.color = color;

        rect = new Rectangle(random.nextInt(100) + 100, random.nextInt(100) + 200, 100, 200);
    }

    @Override
    public void onEvent(Event event){
        Dispatcher dispatcher = new Dispatcher(event);
        dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onPressed((MousePressedEvent) e));
        dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> onReleased((MouseReleasedEvent) e));
        dispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> onMoved((MouseMotionEvent) e));

    }

    public void onRender(Graphics g){
        g.setColor(color);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);

        g.setColor(Color.WHITE);
        g.drawString(name, rect.x + 15, rect.y + 35);

    }

    private boolean onPressed(MousePressedEvent event){
        if (rect.contains(new Point(event.getX(), event.getY()))) {
            dragging = true;
        }
        return dragging;
    }

    private boolean onReleased(MouseReleasedEvent event){
        dragging = false;
        return dragging;
    }

    private boolean onMoved(MouseMotionEvent event){
        if (dragging){
            rect.x += event.getX() - px;
            rect.y += event.getY() - py;
        }
        px = event.getX();
        py = event.getY();

        return dragging;
    }

}
