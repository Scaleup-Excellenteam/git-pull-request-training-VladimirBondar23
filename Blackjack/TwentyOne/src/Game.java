import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class Game extends JFrame implements ActionListener, MouseListener {
    private Player computer;
    private Player player;
    private Deck deck;
    private Deck deck2;
    private JLabel label;
    private ImageIcon icon;
    JLabel currentState;
    Thread thread;
    Runnable deckCreator;
    JButton exitButton, makeDebt, playButton, menuButton, moreButton, enoughButton,continueButton;
    GridBagConstraints gbc;
    JLabel whiteChipLabel, redChipLabel, greenChipLabel, blueChipLabel, debt, playerCardLabel, playerCardLabel2, lose,computerCardLabel,computerCardLabel2, playerBank;
    ImageIcon whiteBigger, whiteChip, redBigger, redChip, greenBigger, greenChip, blueBigger, blueChip, thumbnail, lost, won, computerCard1Icon, computerCard2Icon, draw;
    JPanel menuButtonPanel, debtPanel, playerCardPanel, forMore,losePanel,computerCardPanel, buttons, deckPanel, additionalCardPanel;
    public Game() {
        setUndecorated(true);

        computer = new Player(new Bank());
        player = new Player();
        currentState = new JLabel();
        currentState.setText("Current sum: " + player.getSum());
        currentState.setVisible(false);
        currentState.setFont(new Font("MonoLisa",Font.BOLD,30));
        currentState.setForeground(Color.WHITE);
        currentState.setVerticalAlignment(JLabel.BOTTOM);

        deck = new Deck();
        deckCreator = new Runnable() {
            @Override
            public void run() {
                deck2 = new Deck();
            }
        };
        thread = new Thread(deckCreator);
        thread.start();

        gbc = new GridBagConstraints();

        label = new JLabel();
        icon = new ImageIcon(getClass().getResource("/blackjack.png"));
        icon = scaleIcon(icon);
        label.setIcon(icon);

        setSize(1920,1080);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(label);
        setLayout(new BorderLayout());
        setVisible(true);


        Timer timer = new Timer(2500, e -> openMenu());
        timer.setRepeats(false);
        timer.start();
    }
    public void openMenu(){
        getContentPane().removeAll();
        setLayout(new GridBagLayout());

        icon = new ImageIcon(getClass().getResource("/table.jpg"));
        icon = scaleIcon(icon);
        label.setIcon(icon);
        setContentPane(label);

        exitButton = new JButton("Exit");
        playButton = new JButton("Play");
        exitButton.setPreferredSize(new Dimension(200,60));
        exitButton.setFocusable(false);
        playButton.setPreferredSize(new Dimension(200,60));
        playButton.setFocusable(false);
        exitButton.addActionListener(this);
        playButton.addActionListener(this);
        playButton.setBackground(Color.BLACK);
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(new Color(60,70,110,250));
        playButton.setForeground(new Color(60,70,110,250));
        exitButton.setContentAreaFilled(false);
        exitButton.setOpaque(true);
        playButton.setContentAreaFilled(false);
        playButton.setOpaque(true);
        exitButton.setFont(new Font("Consolas",Font.ITALIC,28));
        playButton.setFont(new Font("Consolas",Font.ITALIC,28));


        JPanel panel = new JPanel();
        panel.setBackground(new Color(0,0,0,0));
        panel.setLayout(new GridLayout(2,1,10,30));

        panel.setPreferredSize(new Dimension(250,150));
        panel.add(playButton);
        panel.add(exitButton);

        add(panel,new GridBagConstraints());
        revalidate();
        repaint();
    }
    public void playing(){
        getContentPane().removeAll();
        setContentPane(label);
        setLayout(new GridLayout(2,3));




        menuButton = new JButton("Main menu");
        menuButton.setHorizontalAlignment(SwingConstants.RIGHT);
        menuButton.setPreferredSize(new Dimension(200,60));
        menuButton.setFocusable(false);
        menuButton.addActionListener(this);
        menuButton.setBackground(Color.YELLOW);
        menuButton.setForeground(Color.WHITE);
        menuButton.setBorderPainted(false);
        menuButton.setFont(new Font("MonoLisa",Font.PLAIN,34));
        menuButton.setContentAreaFilled(false);
        menuButton.setOpaque(false);

        menuButtonPanel = new JPanel(new GridBagLayout());
        menuButtonPanel.setBackground(Color.BLACK);
        menuButtonPanel.setOpaque(false);
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        menuButtonPanel.add(menuButton,gbc);

        debtPanel = new JPanel();
        debtPanel.setLayout(new BoxLayout(debtPanel,BoxLayout.Y_AXIS));
        debtPanel.setPreferredSize(new Dimension(400,120));
        debtPanel.setBackground(Color.CYAN);
        debtPanel.setOpaque(false);
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.SOUTHEAST;

        playerBank = new JLabel();
        playerBank.setText("You have: " + player.getTotal().getStore());
        playerBank.setFont(new Font("MV Boli",Font.BOLD,25));
        playerBank.setForeground(Color.WHITE);

        debt = new JLabel();
        debt.setText("Current debt: " + player.getCurrentDebt());
        debt.setFont(new Font("MV Boli",Font.BOLD,25));
        debt.setForeground(Color.WHITE);

        makeDebt = new JButton("Place a debt");
        makeDebt.setFocusable(false);
        makeDebt.setFont(new Font("Input",Font.PLAIN,25));
        makeDebt.setBorderPainted(false);
        makeDebt.setForeground(Color.WHITE);
        makeDebt.setBackground(Color.BLACK);
        makeDebt.setOpaque(false);
        makeDebt.setContentAreaFilled(false);
        makeDebt.addActionListener(this);

        debtPanel.add(playerBank);
        debtPanel.add(debt);
        debtPanel.add(makeDebt);
        menuButtonPanel.add(debtPanel,gbc);
        add(menuButtonPanel);






        JLabel deckThumbnail = new JLabel();
        thumbnail = new ImageIcon(getClass().getResource("/thumbnail2.jpg"));
        thumbnail = scaleIcon(thumbnail);
        deckThumbnail.setText("Deck");
        deckThumbnail.setForeground(Color.WHITE);
        deckThumbnail.setIcon(thumbnail);
        deckThumbnail.setHorizontalTextPosition(JLabel.CENTER);
        deckThumbnail.setVerticalTextPosition(JLabel.BOTTOM);
        deckThumbnail.setHorizontalAlignment(JLabel.CENTER);
        deckThumbnail.setVerticalAlignment(JLabel.CENTER);
        deckThumbnail.setIconTextGap(15);
        deckThumbnail.setFont(new Font("MV Boli",Font.BOLD,20));

        deckPanel = new JPanel(new BorderLayout());
        deckPanel.setBackground(Color.BLACK);
        deckPanel.setOpaque(false);
        deckPanel.add(deckThumbnail,BorderLayout.SOUTH);
        deckPanel.add(currentState,BorderLayout.CENTER);








        continueButton = makeButton("Continue");
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        lost = new ImageIcon(getClass().getResource("/YOU LOSE.png"));
        lost = growChip(lost);
        won = new ImageIcon(getClass().getResource("/YOU WIN.png"));
        won = growChip(won);
        draw = new ImageIcon(getClass().getResource("/DRAW.png"));
        draw = growChip(draw);
        lose = new JLabel();
        lose.setAlignmentX(Component.CENTER_ALIGNMENT);
        losePanel = new JPanel();
        losePanel.setLayout(new BoxLayout(losePanel,BoxLayout.Y_AXIS));
        losePanel.setBackground(Color.BLACK);
        losePanel.setOpaque(false);
        losePanel.add(lose);
        losePanel.add(continueButton);

        computerCardLabel = new JLabel();
        computerCardLabel.setIcon(thumbnail);
        computerCardLabel2 = new JLabel();
        computerCardLabel2.setIcon(thumbnail);
        JPanel comp = new JPanel(new FlowLayout(FlowLayout.CENTER));
        comp.setBackground(Color.BLACK);
        comp.setOpaque(false);
        computerCardPanel = new JPanel(new BorderLayout());
        computerCardPanel.setBackground(Color.BLACK);
        computerCardPanel.setOpaque(false);

        comp.add(computerCardLabel);
        comp.add(computerCardLabel2);
        computerCardPanel.add(comp,BorderLayout.NORTH);
        computerCardPanel.add(losePanel,BorderLayout.SOUTH);



        additionalCardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        additionalCardPanel.setBackground(Color.BLACK);
        additionalCardPanel.setOpaque(false);
        computerCardPanel.add(additionalCardPanel,BorderLayout.CENTER);

        add(computerCardPanel);
        add(deckPanel);




        JPanel chipPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        chipPanel.setBackground(Color.BLUE);
        chipPanel.setOpaque(false);

        whiteChip = new ImageIcon(getClass().getResource("/WHITE CHIP.png"));
        redChip = new ImageIcon(getClass().getResource("/RED CHIP.png"));
        greenChip = new ImageIcon(getClass().getResource("/GREEN CHIP.png"));
        blueChip = new ImageIcon(getClass().getResource("/BLUE CHIP.png"));

        whiteBigger = growChip(whiteChip);
        redBigger = growChip(redChip);
        greenBigger = growChip(greenChip);
        blueBigger = growChip(blueChip);

        whiteChip = scaleChip(whiteChip);
        redChip = scaleChip(redChip);
        greenChip = scaleChip(greenChip);
        blueChip = scaleChip(blueChip);

        whiteChipLabel = new JLabel(whiteChip);
        whiteChipLabel.addMouseListener(this);
        redChipLabel = new JLabel(redChip);
        redChipLabel.addMouseListener(this);
        greenChipLabel = new JLabel(greenChip);
        greenChipLabel.addMouseListener(this);
        blueChipLabel = new JLabel(blueChip);
        blueChipLabel.addMouseListener(this);

        chipPanel.add(whiteChipLabel);
        chipPanel.add(redChipLabel);
        chipPanel.add(greenChipLabel);
        chipPanel.add(blueChipLabel);
        add(chipPanel);







        playerCardPanel = new JPanel();
        playerCardPanel.setLayout(new BorderLayout());
        playerCardPanel.setBackground(Color.BLACK);
        playerCardPanel.setOpaque(false);

        JPanel cards = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cards.setBackground(Color.BLACK);
        cards.setOpaque(false);
        playerCardPanel.add(cards,BorderLayout.SOUTH);

        forMore = new JPanel(new FlowLayout(FlowLayout.CENTER));
        forMore.setBackground(Color.BLACK);
        forMore.setOpaque(false);
        playerCardPanel.add(forMore,BorderLayout.NORTH);

        playerCardLabel = new JLabel(thumbnail);
        playerCardLabel2 = new JLabel(thumbnail);
        cards.add(playerCardLabel);
        cards.add(playerCardLabel2);
        add(playerCardPanel);







        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setOpaque(false);

        moreButton = makeButton("More");
        enoughButton = makeButton("Enough");

        buttons = new JPanel(new GridLayout(2,1,0,10));
        buttons.setPreferredSize(new Dimension(150,90));
        buttons.setBackground(Color.BLACK);
        buttons.setOpaque(false);
        buttons.add(moreButton);
        buttons.add(enoughButton);

        gbc.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(buttons,gbc);
        add(buttonPanel);

        revalidate();
        repaint();

    }




    public ImageIcon scaleIcon(ImageIcon Icon){
        if (Icon.equals(icon)) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;

            double widthScale = (double) screenWidth / icon.getIconWidth();
            double heightScale = (double) screenHeight / icon.getIconHeight();
            double scale = Math.min(widthScale, heightScale);

            int newWidth = (int) (icon.getIconWidth() * scale);
            int newHeight = (int) (icon.getIconHeight() * scale);
            return new ImageIcon(icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
        }
        else{
            return new ImageIcon(Icon.getImage().getScaledInstance(130, 200, Image.SCALE_SMOOTH));
        }
    }
    public ImageIcon scaleChip(ImageIcon chip){
        double iconWidth = chip.getIconWidth();
        double iconHeight = chip.getIconHeight();
        return new ImageIcon(chip.getImage().getScaledInstance((int)(iconWidth/4),(int)(iconHeight/4),Image.SCALE_SMOOTH));
    }
    public ImageIcon growChip (ImageIcon chip){
        double iconWidth = chip.getIconWidth();
        double iconHeight = chip.getIconHeight();
        return new ImageIcon(chip.getImage().getScaledInstance((int)(iconWidth/3),(int)(iconHeight/3),Image.SCALE_SMOOTH));
    }

    public ImageIcon anotherScale(ImageIcon pic) {
        double iconWidth = pic.getIconWidth();
        double iconHeight = pic.getIconHeight();
        return new ImageIcon(pic.getImage().getScaledInstance((int)(iconWidth/1.25),(int)(iconHeight/1.25),Image.SCALE_SMOOTH));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == whiteChipLabel)
            if (!(player.getCurrentDebt() + 5 > player.getTotal().getStore()))
                player.setCurrentDebt(player.getCurrentDebt() + 5);
        if (e.getSource() == redChipLabel)
            if (!(player.getCurrentDebt() + 10 > player.getTotal().getStore()))
                player.setCurrentDebt(player.getCurrentDebt() + 10);
        if (e.getSource() == greenChipLabel)
            if (!(player.getCurrentDebt() + 20 > player.getTotal().getStore()))
                player.setCurrentDebt(player.getCurrentDebt() + 20);
        if (e.getSource() == blueChipLabel)
            if (!(player.getCurrentDebt() + 50 > player.getTotal().getStore()))
                player.setCurrentDebt(player.getCurrentDebt() + 50);
        debt.setText("Current debt: " + player.getCurrentDebt());
        debtPanel.revalidate();
        debtPanel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == whiteChipLabel)
            ((JLabel)(e.getSource())).setIcon(whiteBigger);
        if (e.getSource() == redChipLabel)
            ((JLabel)(e.getSource())).setIcon(redBigger);
        if (e.getSource() == greenChipLabel)
            ((JLabel)(e.getSource())).setIcon(greenBigger);
        if (e.getSource() == blueChipLabel)
            ((JLabel)(e.getSource())).setIcon(blueBigger);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == whiteChipLabel)
            ((JLabel)(e.getSource())).setIcon(whiteChip);
        if (e.getSource() == redChipLabel)
            ((JLabel)(e.getSource())).setIcon(redChip);
        if (e.getSource() == greenChipLabel)
            ((JLabel)(e.getSource())).setIcon(greenChip);
        if (e.getSource() == blueChipLabel)
            ((JLabel)(e.getSource())).setIcon(blueChip);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton)
            System.exit(0);
        if (e.getSource() == playButton)
            playing();
        if (e.getSource() == menuButton) {
            openMenu();
            deck.deck = new ArrayList<>(deck2.deck);
            deck.size = 52;
            currentState.setVisible(false);
            player.setCurrentDebt(0);
            thread = new Thread(deckCreator);
            thread.start();
        }
        if (e.getSource() == makeDebt) {
            if (player.getCurrentDebt() >0) {
                Card card1 = deck.getCard();
                player.setCard1(card1);
                Card card2 = deck.getCard();
                player.setCard2(card2);
                ImageIcon card1Icon = new ImageIcon(getClass().getResource("/" + card1.getRank() + " OF " + card1.getSign() + ".jpg"));
                card1Icon = scaleIcon(card1Icon);
                playerCardLabel.setIcon(card1Icon);
                ImageIcon card2Icon = new ImageIcon(getClass().getResource("/" + card2.getRank() + " OF " + card2.getSign() + ".jpg"));
                card2Icon = scaleIcon(card2Icon);
                playerCardLabel2.setIcon(card2Icon);
                whiteChipLabel.removeMouseListener(this);
                redChipLabel.removeMouseListener(this);
                greenChipLabel.removeMouseListener(this);
                blueChipLabel.removeMouseListener(this);

                Card computerCard1 = deck.getCard();
                computer.setCard1(computerCard1);
                Card computerCard2 = deck.getCard();
                computer.setCard2(computerCard2);
                computerCard1Icon = new ImageIcon(getClass().getResource("/" + computerCard1.getRank() + " OF " + computerCard1.getSign() + ".jpg"));
                computerCard1Icon = scaleIcon(computerCard1Icon);
                computerCard2Icon = new ImageIcon(getClass().getResource("/" + computerCard2.getRank() + " OF " + computerCard2.getSign() + ".jpg"));
                computerCard2Icon = scaleIcon(computerCard2Icon);
                computerCardLabel.setIcon(computerCard1Icon);


                makeDebt.setVisible(false);
                moreButton.setVisible(true);
                enoughButton.setVisible(true);
                player.setSum(player.countSum());
                computer.setSum(computer.countSum());

                currentState.setVisible(true);
                currentState.setText("Current sum: " + player.getSum());
                buttons.revalidate();
                buttons.repaint();

                if (player.getCard1().getRank() == Rank.ACE && player.getCard2().getRank() == Rank.ACE)
                    youWin();
                if (player.getSum() == 21 && (computer.getSum() != 21))
                    youWin();
                if (computer.getSum() == 21 && (player.getSum() != 21))
                    youLose();
                if (computer.getSum() == 21 && player.getSum() == 21)
                    draw();
            }
        }
        if (e.getSource() == moreButton){
            JLabel moreCard = new JLabel();
            Card oneMore = deck.getCard();
            ImageIcon moreIcon = new ImageIcon(getClass().getResource("/" + oneMore.getRank() + " OF " + oneMore.getSign() + ".jpg"));
            moreIcon = scaleIcon(moreIcon);
            moreIcon = anotherScale(moreIcon);
            moreCard.setIcon(moreIcon);
            forMore.add(moreCard);
            forMore.revalidate();
            forMore.repaint();
            if (oneMore.getRank() == Rank.ACE){
                if (player.getSum() + 11 > 21)
                    player.setSum(player.getSum() + 1);
                else
                    player.setSum(player.getSum() + 11);
            }
            else
                player.setSum(player.getSum() + oneMore.getValue());

            currentState.setText("Current sum: " + player.getSum());
            deckPanel.revalidate();
            deckPanel.repaint();

            if (player.getSum() > 21)
                youLose();
        }
        if (e.getSource() == enoughButton){
            while (computer.getSum() < 17){
                JLabel moreCard = new JLabel();
                Card oneMore = deck.getCard();
                ImageIcon moreIcon = new ImageIcon(getClass().getResource("/" + oneMore.getRank() + " OF " + oneMore.getSign() + ".jpg"));
                moreIcon = scaleIcon(moreIcon);
                moreIcon = anotherScale(moreIcon);
                moreCard.setIcon(moreIcon);
                if (oneMore.getRank() == Rank.ACE){
                    if (computer.getSum() + 11 > 21)
                        computer.setSum(computer.getSum() + 1);
                    else
                        computer.setSum(computer.getSum() + 11);
                }
                else
                    computer.setSum(computer.getSum() + oneMore.getValue());
                additionalCardPanel.add(moreCard);
                additionalCardPanel.revalidate();
                additionalCardPanel.repaint();
            }
            if (computer.getSum() > 21)
                youWin();
            else if (computer.getSum() == player.getSum())
                draw();
            else if (computer.getSum() > player.getSum())
                youLose();
            else
                youWin();
        }
        if (e.getSource() == continueButton){
            deck.deck = new ArrayList<>(deck2.deck);
            deck.size = 52;
            thread = new Thread(deckCreator);
            thread.start();
            playerBank.setText("You have: " + player.getTotal().getStore());
            player.setCurrentDebt(0);
            debt.setText("Current debt: " + player.getCurrentDebt());
            debtPanel.revalidate();
            debtPanel.repaint();
            forMore.removeAll();
            forMore.revalidate();
            forMore.repaint();
            additionalCardPanel.removeAll();
            additionalCardPanel.revalidate();
            additionalCardPanel.repaint();
            whiteChipLabel.addMouseListener(this);
            redChipLabel.addMouseListener(this);
            greenChipLabel.addMouseListener(this);
            blueChipLabel.addMouseListener(this);
            moreButton.setEnabled(true);
            enoughButton.setEnabled(true);
            moreButton.setVisible(false);
            enoughButton.setVisible(false);
            computerCardLabel.setIcon(thumbnail);
            computerCardLabel2.setIcon(thumbnail);
            makeDebt.setVisible(true);
            playerCardLabel.setIcon(thumbnail);
            playerCardLabel2.setIcon(thumbnail);
            continueButton.setVisible(false);
            lose.setVisible(false);
            currentState.setVisible(false);
        }
    }
    public void youLose(){
        lose.setVisible(true);
        lose.setIcon(lost);
        moreButton.setEnabled(false);
        enoughButton.setEnabled(false);
        computerCardLabel.setIcon(computerCard1Icon);
        computerCardLabel2.setIcon(computerCard2Icon);
        computer.getTotal().setStore(computer.getTotal().getStore() + player.getCurrentDebt());
        player.getTotal().setStore(player.getTotal().getStore() - player.getCurrentDebt());
        if (!(player.getTotal().getStore() == 0))
            continueButton.setVisible(true);
    }
    public void youWin(){
        lose.setVisible(true);
        lose.setIcon(won);
        moreButton.setEnabled(false);
        enoughButton.setEnabled(false);
        continueButton.setVisible(true);
        computerCardLabel.setIcon(computerCard1Icon);
        computerCardLabel2.setIcon(computerCard2Icon);
        player.getTotal().setStore(player.getTotal().getStore() + player.getCurrentDebt());
        computer.getTotal().setStore(computer.getTotal().getStore() - player.getCurrentDebt());
        if (!(computer.getTotal().getStore() == 0))
            continueButton.setVisible(true);
    }
    public void draw(){
        lose.setVisible(true);
        lose.setIcon(draw);
        moreButton.setEnabled(false);
        enoughButton.setEnabled(false);
        continueButton.setVisible(true);
        computerCardLabel.setIcon(computerCard1Icon);
        computerCardLabel2.setIcon(computerCard2Icon);
    }

    public JButton makeButton (String text){
        JButton button = new JButton(text);
        button.setBackground(Color.BLACK);
        button.setFocusable(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("MonoLisa",Font.BOLD,30));
        button.addActionListener(this);
        button.setVisible(false);
        return button;
    }
}
