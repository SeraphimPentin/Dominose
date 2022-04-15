package userInterface;

import core.Domino;
import core.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class DominoesUI extends JFrame {


    private static final long serialVersionUID = -2734434886071421347L;

    public static void main(String[] args) {
        new DominoesUI();

    }

    //variables
    private int userChoiceDominoIndex = -1;
    private boolean successfulMove = false;
    private Game game;
    private ArrayList<JButton> playerHand;
    private ArrayList<JLabel> playedLabel2;
    //Board Variables
    private JPanel boardPanel;
    //Player Hand Variables
    private JPanel playerHandPanel;
    private JButton endGameButton;

    //кнопка перед выбором домино
    private class PlayerHandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            for (int i = 0; i < game.player.dominoCount(); i++) {
                if (ae.getSource() == playerHand.get(i)) {
                    userChoiceDominoIndex = i;
                }
            }
            playerHand.get(userChoiceDominoIndex).setContentAreaFilled(true);
            playerHand.get(userChoiceDominoIndex).removeActionListener(this);
            playerHand.get(userChoiceDominoIndex).addActionListener(new PlayerHandListener2());
            for (int j = 0; j < game.player.dominoCount(); j++) {
                if (j != userChoiceDominoIndex) {
                    playerHand.get(j).setEnabled(false);
                }
            }

            successfulMove = game.doEndMove(game.player.getDominoFromHand(userChoiceDominoIndex));

            if (successfulMove) {
                game.player.removeDominoFromHand(userChoiceDominoIndex);

                Domino firstDomino = game.playedDominoes.get(0);
                Domino lastDomino = game.playedDominoes.get(game.playedDominoes.size() - 1);

                game.player.addToScore(firstDomino.getLeftVal() + lastDomino.getRightVal());

                playerHand.get(userChoiceDominoIndex).setVisible(false);
                playerHand.remove(userChoiceDominoIndex);

                boardPanel.removeAll();
                boardPanel.setVisible(false);
                boardPanel.setVisible(true);


                for (int i = 0; i < game.playedDominoes.size(); i++) {
                    ImageIcon test = new ImageIcon("src\\main\\java\\images\\sideways\\" + game.playedDominoes.get(i).toValue() + ".png");
                    Image scaleImage;
                    if (game.playedDominoes.get(i).isDouble()) {
                        scaleImage = test.getImage().getScaledInstance(60, 90, Image.SCALE_DEFAULT);
                        ImageIcon icon = new ImageIcon(scaleImage);
                        JLabel played = new JLabel(icon);
                        playedLabel2.add(i, played);
                    } else {
                        scaleImage = test.getImage().getScaledInstance(90, 60, Image.SCALE_DEFAULT);
                        ImageIcon icon = new ImageIcon(scaleImage);
                        JLabel played = new JLabel(icon);
                        playedLabel2.add(i, played);
                    }
                    boardPanel.add(playedLabel2.get(i));
                }


                for (int j = 0; j < game.player.dominoCount(); j++) {
                    playerHand.get(j).setEnabled(true);
                }
                return;
            }

            successfulMove = game.doBegMove(game.player.getDominoFromHand(userChoiceDominoIndex));

            if (successfulMove) {
                game.player.removeDominoFromHand(userChoiceDominoIndex);

                Domino firstDomino = game.playedDominoes.get(0);
                Domino lastDomino = game.playedDominoes.get(game.playedDominoes.size() - 1);

                game.player.addToScore(firstDomino.getLeftVal() + lastDomino.getRightVal());

                playerHand.get(userChoiceDominoIndex).setVisible(false);
                playerHand.remove(userChoiceDominoIndex);

                boardPanel.removeAll();
                boardPanel.setVisible(false);
                boardPanel.setVisible(true);

                for (int i = 0; i < game.playedDominoes.size(); i++) {
                    ImageIcon test = new ImageIcon("src\\main\\java\\images\\sideways\\" + game.playedDominoes.get(i).toValue() + ".png");
                    Image scaleImage;
                    if (game.playedDominoes.get(i).isDouble()) {
                        scaleImage = test.getImage().getScaledInstance(60, 90, Image.SCALE_DEFAULT);
                        ImageIcon icon = new ImageIcon(scaleImage);
                        JLabel played = new JLabel(icon);
                        playedLabel2.add(i, played);
                    } else {
                        scaleImage = test.getImage().getScaledInstance(90, 60, Image.SCALE_DEFAULT);
                        ImageIcon icon = new ImageIcon(scaleImage);
                        JLabel played = new JLabel(icon);
                        playedLabel2.add(i, played);
                    }
                    boardPanel.add(playedLabel2.get(i));


                }

                for (int j = 0; j < game.player.dominoCount(); j++) {
                    playerHand.get(j).setEnabled(true);
                }
            }
        }
    }

    //кнопка после выделения
    private class PlayerHandListener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println("test");
            userChoiceDominoIndex = -1;
            for (int i = 0; i < game.player.dominoCount(); i++) {
                if (ae.getSource() == playerHand.get(i)) {
                    userChoiceDominoIndex = i;
                }
            }
            playerHand.get(userChoiceDominoIndex).setContentAreaFilled(false);
            playerHand.get(userChoiceDominoIndex).removeActionListener(this);
            playerHand.get(userChoiceDominoIndex).addActionListener(new PlayerHandListener());
            for (int j = 0; j < game.player.dominoCount(); j++) {
                if (j != userChoiceDominoIndex) {
                    playerHand.get(j).setEnabled(true);
                }
            }
        }
    }


    //кнопка закончить игру
    private class EndGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new DominoesUI();
        }
    }

    //создание игры визуал
    public DominoesUI() {
        initGame();
        initComponents();
    }

    private void initGame() {
        game = new Game();
        game.initGame();
        game.doFirstMove();


    }

    private void initComponents() {
        this.setTitle("Group 5: Block Dominoes");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1200, 800));
        this.setMinimumSize(new Dimension(1200, 800));


        /*Board*/
        //Board Panel
        boardPanel = new JPanel();
        boardPanel.setMinimumSize(new Dimension(750, 389));
        boardPanel.setMaximumSize(new Dimension(750, 389));
        boardPanel.setPreferredSize(new Dimension(750, 389));
        boardPanel.setLayout(new GridBagLayout());

        //Played Label
        playedLabel2 = new ArrayList<JLabel>();
        //System.out.println(game.ToStringPlayedTiles());
        boardPanel.removeAll();
        for (int i = 0; i < game.playedDominoes.size(); i++) {
            ImageIcon test = new ImageIcon("src\\main\\java\\images\\sideways\\" + game.playedDominoes.get(i).toValue() + ".png");
            Image scaleImage;
            if (game.playedDominoes.get(i).isDouble()) {
                scaleImage = test.getImage().getScaledInstance(60, 90, Image.SCALE_DEFAULT);
                ImageIcon icon = new ImageIcon(scaleImage);
                JLabel played = new JLabel(icon);
                playedLabel2.add(i, played);
            } else {
                scaleImage = test.getImage().getScaledInstance(90, 60, Image.SCALE_DEFAULT);
                ImageIcon icon = new ImageIcon(scaleImage);
                JLabel played = new JLabel(icon);
                playedLabel2.add(i, played);
            }
            boardPanel.add(playedLabel2.get(i));
        }

        //player Hand panel
        playerHandPanel = new JPanel(new GridLayout(6, 27));
        playerHandPanel.setMinimumSize(new Dimension(400, 400));
        playerHandPanel.setMaximumSize(new Dimension(400, 400));
        playerHandPanel.setPreferredSize(new Dimension(400, 400));
        playerHandPanel.setBorder(BorderFactory.createTitledBorder(game.player.getName() + "'s Hand"));

        //End Game Button
        endGameButton = new JButton("End Game");
        endGameButton.addActionListener(new EndGameListener());


        //players Hand Buttons
        playerHand = new ArrayList<JButton>();

        for (int i = 0; i < 27; i++) {
            ImageIcon test = new ImageIcon("src\\main\\java\\images\\sideways\\" + game.player.getDominoFromHand(i).toValue() + ".png");
            Image scaleImage = test.getImage().getScaledInstance(80, 120, Image.SCALE_DEFAULT);
            ImageIcon test2 = new ImageIcon(scaleImage);
            JButton playerHandButton = new JButton(test2);
            playerHand.add(playerHandButton);
            playerHand.get(i).setContentAreaFilled(false);
            playerHand.get(i).setBorder(null);
            playerHandPanel.add(playerHand.get(i));
            playerHand.get(i).addActionListener(new PlayerHandListener());
        }


        //Add to UI

        this.add(boardPanel, BorderLayout.CENTER);
        this.add(playerHandPanel, BorderLayout.WEST);

        this.setVisible(true);
    }

}
