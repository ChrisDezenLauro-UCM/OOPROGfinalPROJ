import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FirstSleepSuggestPrototype {

    // ------------------------
    // Quick tips for the user
    // ------------------------
    static ArrayList<String> quickTips = new ArrayList<>();
    static int tipIndex = 0; // keeps track of which tip is displayed

    // ------------------------
    // Components for usual sleep schedule
    // ------------------------
    static JComboBox<Integer> startHourBox, startMinuteBox;
    static JComboBox<String> startAMPMBox;
    static JComboBox<Integer> endHourBox, endMinuteBox;
    static JComboBox<String> endAMPMBox;
    static JComboBox<String> scheduleFormatBox;

    // ------------------------
    // Components for target wake-up time
    // ------------------------
    static JComboBox<Integer> wakeHourBox, wakeMinuteBox;
    static JComboBox<String> wakeAMPMBox;
    static JComboBox<String> wakeFormatBox;

    // ------------------------
    // Dark mode toggle state
    // ------------------------
    static boolean isDarkMode = false;

    // ------------------------
    // Main method: start program
    // ------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FirstSleepSuggestPrototype::startQuestions);
    }

    // ------------------------
    // Initial questions to the user
    // ------------------------
    public static void startQuestions() {
        int sleptWell = JOptionPane.showConfirmDialog(null,
                "Hey, have you slept well recently?",
                "Sleep Check",
                JOptionPane.YES_NO_OPTION);

        int needSched = JOptionPane.showConfirmDialog(null,
                "You need a new sleep sched?",
                "Schedule Reset",
                JOptionPane.YES_NO_OPTION);

        if (needSched == JOptionPane.NO_OPTION) {
            // Go back to the first question if user doesn't want a new schedule
            startQuestions();
            return;
        }

        // Show schedule input window if the user wants to reset schedule
        showScheduleInputWindow();
    }

    // ------------------------
    // Window for selecting user's usual sleep schedule and target wake-up
    // ------------------------
    public static void showScheduleInputWindow() {
        JFrame frame = new JFrame("Sleep Schedule Input");
        frame.setSize(500, 400);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Drop-down for selecting time format (12h or 24h)
        scheduleFormatBox = new JComboBox<>(new String[]{"24-Hour Format", "12-Hour Format"});
        wakeFormatBox = new JComboBox<>(new String[]{"24-Hour Format", "12-Hour Format"});

        // Initialize all hour/minute combo boxes
        startHourBox = new JComboBox<>();
        startMinuteBox = new JComboBox<>();
        endHourBox = new JComboBox<>();
        endMinuteBox = new JComboBox<>();
        wakeHourBox = new JComboBox<>();
        wakeMinuteBox = new JComboBox<>();

        // AM/PM combo boxes for 12-hour format
        startAMPMBox = new JComboBox<>(new String[]{"AM", "PM"});
        endAMPMBox = new JComboBox<>(new String[]{"AM", "PM"});
        wakeAMPMBox = new JComboBox<>(new String[]{"AM", "PM"});

        // Fill minutes in increments of 5
        for (int i = 0; i < 60; i += 5) {
            startMinuteBox.addItem(i);
            endMinuteBox.addItem(i);
            wakeMinuteBox.addItem(i);
        }

        // Load 24-hour format by default
        loadHours24(startHourBox);
        loadHours24(endHourBox);
        loadHours24(wakeHourBox);

        // Set default selections
        startHourBox.setSelectedIndex(0);
        startMinuteBox.setSelectedIndex(0);
        endHourBox.setSelectedIndex(0);
        endMinuteBox.setSelectedIndex(0);
        wakeHourBox.setSelectedIndex(0);
        wakeMinuteBox.setSelectedIndex(0);
        startAMPMBox.setSelectedIndex(0);
        endAMPMBox.setSelectedIndex(0);
        wakeAMPMBox.setSelectedIndex(0);

        // Hide AM/PM for 24-hour format initially
        startAMPMBox.setVisible(false);
        endAMPMBox.setVisible(false);
        wakeAMPMBox.setVisible(false);

        // Add listeners for format switching
        scheduleFormatBox.addActionListener(e -> switchScheduleFormat());
        wakeFormatBox.addActionListener(e -> switchWakeFormat());

        // Create panels for each input section
        JPanel formatPanel1 = new JPanel();
        formatPanel1.add(new JLabel("Sleep Schedule Format:"));
        formatPanel1.add(scheduleFormatBox);

        JPanel startPanel = new JPanel();
        startPanel.add(new JLabel("Start:"));
        startPanel.add(startHourBox);
        startPanel.add(new JLabel(":"));
        startPanel.add(startMinuteBox);
        startPanel.add(startAMPMBox);

        JPanel endPanel = new JPanel();
        endPanel.add(new JLabel("End:"));
        endPanel.add(endHourBox);
        endPanel.add(new JLabel(":"));
        endPanel.add(endMinuteBox);
        endPanel.add(endAMPMBox);

        JPanel formatPanel2 = new JPanel();
        formatPanel2.add(new JLabel("Wake-Up Format:"));
        formatPanel2.add(wakeFormatBox);

        JPanel wakePanel = new JPanel();
        wakePanel.add(new JLabel("Wake-Up:"));
        wakePanel.add(wakeHourBox);
        wakePanel.add(new JLabel(":"));
        wakePanel.add(wakeMinuteBox);
        wakePanel.add(wakeAMPMBox);

        // Confirm button to proceed to main window
        JButton confirmBtn = new JButton("Confirm Schedule");
        confirmBtn.addActionListener(e -> {
            String wakeTime = getWakeTime();
            if (wakeTime == null) return;

            String recommendedSleep = calculateRecommendedSleep(wakeTime);

            frame.dispose();
            createMainWindow(getScheduleString(), wakeTime, recommendedSleep);
        });

        // Add all panels and button to frame
        frame.add(formatPanel1);
        frame.add(startPanel);
        frame.add(endPanel);
        frame.add(formatPanel2);
        frame.add(wakePanel);
        frame.add(confirmBtn);

        frame.setVisible(true);
    }

    // ------------------------
    // Switch schedule format between 12h and 24h
    // ------------------------
    public static void switchScheduleFormat() {
        boolean to12h = scheduleFormatBox.getSelectedIndex() == 1;
        toggleFormat(startHourBox, startAMPMBox, to12h);
        toggleFormat(endHourBox, endAMPMBox, to12h);
    }

    public static void switchWakeFormat() {
        boolean to12h = wakeFormatBox.getSelectedIndex() == 1;
        toggleFormat(wakeHourBox, wakeAMPMBox, to12h);
    }

    // ------------------------
    // Convert combo box hours for 12h or 24h format
    // ------------------------
    public static void toggleFormat(JComboBox<Integer> hourBox, JComboBox<String> ampmBox, boolean to12h) {
        int hour = hourBox.getSelectedItem() != null ? (Integer) hourBox.getSelectedItem() : 0;

        if (to12h) {
            ampmBox.setVisible(true);
            if (hour == 0) hour = 12;
            else if (hour > 12) hour -= 12;
            loadHours12(hourBox, hour);
            hourBox.setSelectedItem(hour);
        } else {
            if (ampmBox.getSelectedItem() != null) {
                String ampm = (String) ampmBox.getSelectedItem();
                if (ampm.equals("PM") && hour != 12) hour += 12;
                if (ampm.equals("AM") && hour == 12) hour = 0;
            }
            ampmBox.setVisible(false);
            loadHours24(hourBox);
            hourBox.setSelectedItem(hour);
        }
    }

    // ------------------------
    // Fill hour combo box for 24-hour format
    // ------------------------
    public static void loadHours24(JComboBox<Integer> box) {
        box.removeAllItems();
        for (int i = 0; i < 24; i++) box.addItem(i);
    }

    // ------------------------
    // Fill hour combo box for 12-hour format
    // ------------------------
    public static void loadHours12(JComboBox<Integer> box, int selectedHour) {
        box.removeAllItems();
        for (int i = 1; i <= 12; i++) box.addItem(i);
        box.setSelectedItem(selectedHour);
    }

    // ------------------------
    // Get user's usual sleep schedule string
    // ------------------------
    public static String getScheduleString() {
        String start = getTimeString(startHourBox, startMinuteBox, startAMPMBox, scheduleFormatBox);
        String end = getTimeString(endHourBox, endMinuteBox, endAMPMBox, scheduleFormatBox);
        return start + " - " + end;
    }

    // ------------------------
    // Get user's target wake-up time string
    // ------------------------
    public static String getWakeTime() {
        return getTimeString(wakeHourBox, wakeMinuteBox, wakeAMPMBox, wakeFormatBox);
    }

    // ------------------------
    // Helper function to format time string
    // ------------------------
    public static String getTimeString(JComboBox<Integer> hourBox, JComboBox<Integer> minBox,
                                       JComboBox<String> ampmBox, JComboBox<String> formatBox) {
        Integer hour = (Integer) hourBox.getSelectedItem();
        Integer min = (Integer) minBox.getSelectedItem();
        if (hour == null || min == null) return null;

        if (formatBox.getSelectedIndex() == 0) return String.format("%02d:%02d", hour, min);
        String ampm = ampmBox.getSelectedItem() != null ? (String) ampmBox.getSelectedItem() : "AM";
        return String.format("%02d:%02d %s", hour, min, ampm);
    }

    // ------------------------
    // Calculate recommended sleep time (8 hours before wake-up)
    // ------------------------
    public static String calculateRecommendedSleep(String wake) {
        try {
            boolean is12Hr = wake.toUpperCase().contains("AM") || wake.toUpperCase().contains("PM");
            int hour, min;
            if (is12Hr) {
                String[] split = wake.split(" ");
                String[] hm = split[0].split(":");
                hour = Integer.parseInt(hm[0]);
                min = Integer.parseInt(hm[1]);
                String ampm = split[1].toUpperCase();
                if (ampm.equals("PM") && hour != 12) hour += 12;
                if (ampm.equals("AM") && hour == 12) hour = 0;
            } else {
                String[] hm = wake.split(":");
                hour = Integer.parseInt(hm[0]);
                min = Integer.parseInt(hm[1]);
            }

            hour -= 8; // subtract 8 hours for recommended sleep
            if (hour < 0) hour += 24;

            String newAMPM = (hour >= 12) ? "PM" : "AM";
            int displayHour = hour;
            if (displayHour == 0) displayHour = 12;
            else if (displayHour > 12) displayHour -= 12;

            return String.format("%02d:%02d %s", displayHour, min, newAMPM);
        } catch (Exception e) {
            return "Error";
        }
    }

    // ------------------------
    // Main window that displays tips and sleep schedule
    // ------------------------
    public static void createMainWindow(String usual, String wake, String recommended) {
        quickTips.clear();
        quickTips.add("Drinking cold water helps keep you awake.");
        quickTips.add("Counting 1–100 helps you fall asleep faster.");
        quickTips.add("Avoid screens 30 minutes before bed.");
        quickTips.add("Bright light exposure resets your body clock.");
        quickTips.add("20-minute nap boosts energy.");

        JFrame frame = new JFrame("Sleep Schedule Assistant");
        frame.setSize(820, 560);
        frame.setLayout(new BorderLayout(12, 12));
        frame.getContentPane().setBackground(new Color(250, 250, 250));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // ------------------------
        // Dark mode toggle button
        // ------------------------
        JButton darkModeBtn = new JButton("Toggle Dark Mode");
        darkModeBtn.addActionListener(e -> {
            isDarkMode = !isDarkMode;
            updateTheme(frame.getContentPane());
        });

        // Top panel with schedule info and dark mode button
        JPanel top = new JPanel(new GridLayout(4, 1));
        top.setBackground(new Color(250, 250, 250));
        JLabel usualLabel = new JLabel("Your Usual Sleep Schedule: " + usual, SwingConstants.CENTER);
        usualLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JLabel wakeLabel = new JLabel("Target Wake-Up Time: " + wake, SwingConstants.CENTER);
        wakeLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        JLabel recLabel = new JLabel("Recommended Sleep Time: " + recommended, SwingConstants.CENTER);
        recLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        top.add(usualLabel);
        top.add(wakeLabel);
        top.add(recLabel);
        top.add(darkModeBtn);
        frame.add(top, BorderLayout.NORTH);

        // Center panel with general tips
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 6, 6));
        centerPanel.setBackground(new Color(250, 250, 250));
        JLabel generalTips = new JLabel(
                "<html><center>"
                        + "<b>Tips to stay awake:</b> Drink water, keep lights bright.<br>"
                        + "<b>Tips to fall asleep faster:</b> Slow breathing, relax muscles."
                        + "</center></html>",
                SwingConstants.CENTER
        );
        generalTips.setFont(new Font("SansSerif", Font.PLAIN, 16));

        // Quick tip bar with arrows
        JPanel tipBar = new JPanel(new BorderLayout());
        tipBar.setBackground(new Color(250, 250, 250));
        JButton left = new JButton("<");
        JButton right = new JButton(">");
        JLabel tipLabel = new JLabel(quickTips.get(tipIndex), SwingConstants.CENTER);
        left.addActionListener(e -> {
            tipIndex--;
            if (tipIndex < 0) tipIndex = quickTips.size() - 1;
            tipLabel.setText(quickTips.get(tipIndex));
        });
        right.addActionListener(e -> {
            tipIndex++;
            if (tipIndex >= quickTips.size()) tipIndex = 0;
            tipLabel.setText(quickTips.get(tipIndex));
        });
        tipBar.add(left, BorderLayout.WEST);
        tipBar.add(tipLabel, BorderLayout.CENTER);
        tipBar.add(right, BorderLayout.EAST);

        centerPanel.add(generalTips);
        centerPanel.add(tipBar);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Side tips panels
        frame.add(createSideTipsPanel("Night Tips", "Dim lights", "Avoid caffeine", "Warm shower"), BorderLayout.WEST);
        frame.add(createSideTipsPanel("Morning Tips", "Drink water", "Get bright sunlight", "Light exercise"), BorderLayout.EAST);

        // Exit button
        JButton exit = new JButton("EXIT");
        exit.setFont(new Font("SansSerif", Font.BOLD, 16));
        exit.addActionListener(e -> System.exit(0));
        frame.add(exit, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // ------------------------
    // Update colors for dark/light mode
    // ------------------------
    public static void updateTheme(Container container) {
        Color bg = isDarkMode ? new Color(45, 45, 45) : new Color(250, 250, 250);
        Color fg = isDarkMode ? Color.WHITE : Color.BLACK;

        for (Component comp : container.getComponents()) {
            comp.setBackground(bg);
            comp.setForeground(fg);
            if (comp instanceof Container) updateTheme((Container) comp);
        }
        container.repaint();
    }

    // ------------------------
    // Side tips panel generator
    // ------------------------
    public static JPanel createSideTipsPanel(String title, String... tips) {
        JPanel p = new JPanel(new GridLayout(tips.length + 1, 1));
        p.setBackground(new Color(240, 240, 240));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel header = new JLabel("<html><center><b>" + title + "</b></center></html>", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 16));
        p.add(header);
        for (String t : tips) {
            JLabel tip = new JLabel("• " + t, SwingConstants.CENTER);
            tip.setFont(new Font("SansSerif", Font.PLAIN, 14));
            p.add(tip);
        }
        return p;
    }

    /*
     * ------------------------
     * Summary:
     * ------------------------
     * Problem:
     * Many people have irregular sleep schedules and struggle to wake up on time for classes or work.
     * They may need guidance on when to sleep, how to fall asleep faster, and how to stay awake during the day.
     *
     * Solution:
     * This Java program creates a user-friendly GUI that allows users to input their current sleep schedule
     * and target wake-up time. It calculates the recommended sleep start time, provides tips for falling asleep
     * and staying awake, and displays quick tips that the user can cycle through. We added a Dark Mode toggle
     * for better usability at night.
     *
     * What we solved:
     * - Automatic calculation of recommended sleep start time.
     * - Clear visualization of usual sleep schedule, target wake-up, and tips.
     * - Dynamic 12/24-hour format selection.
     * - Quick tip bar with arrows for interactive advice.
     * - Dark Mode support for night-time use.
     */
}
