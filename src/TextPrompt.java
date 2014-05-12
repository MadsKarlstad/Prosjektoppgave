/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by Erlend on 04/05/14.
 */
public class TextPrompt extends JLabel
        implements FocusListener, DocumentListener {

    public enum Show {

        ALWAYS,
        FOCUS_GAINED,
        FOCUS_LOST;
    }
    private JTextComponent component;
    private Document document;
    private Show show;
    private int focusLost;

    public TextPrompt(String text, JTextComponent component) {
        this(text, component, Show.ALWAYS);
    }

    public TextPrompt(String text, JTextComponent component, Show show) {
        this.component = component;
        setShow(show);
        document = component.getDocument();

        setText(text);
        setFont(component.getFont());
        setForeground(component.getForeground());
        setBorder(new EmptyBorder(component.getInsets()));
        setHorizontalAlignment(JLabel.LEADING);

        component.addFocusListener(this);
        document.addDocumentListener(this);

        component.setLayout(new BorderLayout());
        component.add(this);
        checkForPrompt();
    }


    public void changeAlpha(float alpha) {
        changeAlpha((int) (alpha * 255));
    }


    public void changeAlpha(int alpha) {
        alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;

        Color foreground = getForeground();
        int red = foreground.getRed();
        int green = foreground.getGreen();
        int blue = foreground.getBlue();

        Color withAlpha = new Color(red, green, blue, alpha);
        super.setForeground(withAlpha);
    }

    public void setShow(Show show) {
        this.show = show;
    }


    private void checkForPrompt() {

        if (document.getLength() > 0) {
            setVisible(false);
            return;
        }

        if (component.hasFocus()) {
            if (show == Show.ALWAYS
                    || show == Show.FOCUS_GAINED) {
                setVisible(true);
            } else {
                setVisible(false);
            }
        } else {
            if (show == Show.ALWAYS
                    || show == Show.FOCUS_LOST) {
                setVisible(true);
            } else {
                setVisible(false);
            }
        }
    }

    //  Implement FocusListener
    public void focusGained(FocusEvent e) {
        checkForPrompt();
    }

    public void focusLost(FocusEvent e) {
        focusLost++;
        checkForPrompt();
    }

    //  Implement DocumentListener
    public void insertUpdate(DocumentEvent e) {
        checkForPrompt();
    }

    public void removeUpdate(DocumentEvent e) {
        checkForPrompt();
    }

    public void changedUpdate(DocumentEvent e) {
    }
}