import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;

/**
 * VetLineGroupPrototype.java
 * 
 * Multi-screen veterinary clinic prototype using Java Swing.
 * Includes Exit buttons on all screens at the top-left corner.
 */

public class VetLineGroupPrototype {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OpeningScreen());
    }

    // ------------------ SCREEN 1: Opening Window ------------------
    static class OpeningScreen extends JFrame {
        public OpeningScreen() {
            setTitle("VetLine Prototype");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 300);
            setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(Color.WHITE);
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

            // Top panel for Exit button
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e -> System.exit(0));
            topPanel.add(exitButton);
            mainPanel.add(topPanel);

            // Title label
            JLabel titleLabel = new JLabel("VetLine Prototype");
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 20, 0));

            // Begin button
            JButton beginButton = new JButton("The fur-st step begins here!");
            beginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            beginButton.setFocusPainted(false);
            beginButton.addActionListener(e -> {
                dispose();
                new LocationSelectionScreen();
            });

            mainPanel.add(titleLabel);
            mainPanel.add(beginButton);
            add(mainPanel);
            setVisible(true);
        }
    }

    // ------------------ SCREEN 2: Location Selection ------------------
    static class LocationSelectionScreen extends JFrame {
        public LocationSelectionScreen() {
            setTitle("VetLine Prototype - Location");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 300);
            setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(Color.WHITE);
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

            // Top panel for Exit button
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e -> System.exit(0));
            topPanel.add(exitButton);
            mainPanel.add(topPanel);

            // Header
            JLabel titleLabel = new JLabel("VetLine Prototype");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

            JLabel welcomeLabel = new JLabel("Welcome!");
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
            welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel subText = new JLabel("<html><div style='text-align:center;'>Please select your location. This step is important so we can find nearby clinics for you.</div></html>");
            subText.setFont(new Font("Arial", Font.PLAIN, 14));
            subText.setAlignmentX(Component.CENTER_ALIGNMENT);
            subText.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

            String[] locations = {"Cebu City", "Mandaue City", "Lapu-Lapu City"};
            JComboBox<String> locationDropdown = new JComboBox<>(locations);
            locationDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton confirmButton = new JButton("CONFIRM");
            confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            confirmButton.setFocusPainted(false);
            confirmButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
            confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
            confirmButton.addActionListener(e -> {
                String selectedLocation = (String) locationDropdown.getSelectedItem();
                dispose();
                new ClinicListScreen(selectedLocation);
            });

            mainPanel.add(titleLabel);
            mainPanel.add(welcomeLabel);
            mainPanel.add(subText);
            mainPanel.add(locationDropdown);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            mainPanel.add(confirmButton);

            add(mainPanel);
            setVisible(true);
        }
    }

    // ------------------ SCREEN 3: Clinic Catalog ------------------
    static class ClinicListScreen extends JFrame {
        public ClinicListScreen(String location) {
            setTitle("VetLine Prototype - Clinics in " + location);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 500);
            setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(Color.LIGHT_GRAY);
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

            // Top panel for Exit button
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e -> System.exit(0));
            topPanel.add(exitButton);
            mainPanel.add(topPanel);

            JLabel title = new JLabel("Veterinary Clinic Catalog");
            title.setFont(new Font("Arial", Font.BOLD, 20));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            title.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));

            JLabel subtitle = new JLabel("Results are being shown based on your selected location.");
            subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
            subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            subtitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

            mainPanel.add(title);
            mainPanel.add(subtitle);

            // Clinic array
            Clinic[] clinics = {
                    new Clinic("Animal Kingdom", "Daily: 8 AM - 8 PM", "Gorordo Ave, " + location,
                            "https://maps.app.goo.gl/dKLCB2tuQG2oHr9E9"),
                    new Clinic("Cebu Veterinary Doctors", "Daily: 8 AM - 8 PM", "F. Ramos St, " + location,
                            "https://maps.app.goo.gl/NSLAhSCdwqry6QhA8"),
                    new Clinic("Caminade Animal Hospital", "Mon-Sat: 8 AM - 8 PM", "Guadalupe, " + location,
                            "https://maps.app.goo.gl/fBnKCmbLFV8MC5mp7")
            };

            // Create clickable cards
            for (Clinic clinic : clinics) {
                JPanel card = new JPanel();
                card.setMaximumSize(new Dimension(450, 100));
                card.setBackground(new Color(173, 216, 230));
                card.setLayout(new BorderLayout());
                card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JPanel photo = new JPanel();
                photo.setPreferredSize(new Dimension(80, 80));
                photo.setBackground(Color.DARK_GRAY);
                card.add(photo, BorderLayout.WEST);

                JPanel info = new JPanel();
                info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
                JLabel name = new JLabel(clinic.name);
                name.setFont(new Font("Arial", Font.BOLD, 16));
                JLabel hours = new JLabel(clinic.hours);
                hours.setFont(new Font("Arial", Font.PLAIN, 12));
                JLabel address = new JLabel(clinic.address);
                address.setFont(new Font("Arial", Font.PLAIN, 12));
                info.add(name);
                info.add(hours);
                info.add(address);
                card.add(info, BorderLayout.CENTER);

                card.setCursor(new Cursor(Cursor.HAND_CURSOR));
                card.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        dispose();
                        new ClinicDetailScreen(clinic, location);
                    }
                });

                mainPanel.add(card);
                mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }

            JScrollPane scrollPane = new JScrollPane(mainPanel);
            scrollPane.setBorder(null);
            add(scrollPane);
            setVisible(true);
        }
    }

    // ------------------ SCREEN 4: Clinic Services & Info ------------------
    static class ClinicDetailScreen extends JFrame {
        public ClinicDetailScreen(Clinic clinic, String location) {
            setTitle(clinic.name + " - Services");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 500);
            setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Exit button top-left
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e -> System.exit(0));
            topPanel.add(exitButton);
            mainPanel.add(topPanel);

            JLabel title = new JLabel(clinic.name);
            title.setFont(new Font("Arial", Font.BOLD, 20));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(title);

            JTextArea services = new JTextArea(
                    "Services:\n" +
                            "- Consultation & general veterinary care\n" +
                            "- Diagnostics & laboratory services\n" +
                            "- Surgeries & dental procedures\n" +
                            "- Pet pharmacy, supplies, wellness/preventive care\n" +
                            "- Additional services: boarding, grooming, pet products"
            );
            services.setEditable(false);
            services.setFont(new Font("Arial", Font.PLAIN, 14));
            services.setLineWrap(true);
            services.setWrapStyleWord(true);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            mainPanel.add(services);

            JTextArea contact = new JTextArea(
                    "Contact Info:\n" +
                            "Phone: (032) 256-3056\n" +
                            "Emergency: 24-Hour ER\n" +
                            "Address: Marijoy Building (Unit 107-109), 306 F. Ramos St., " + location + "\n" +
                            "Postal Code: 6000, Cebu, Philippines"
            );
            contact.setEditable(false);
            contact.setFont(new Font("Arial", Font.PLAIN, 14));
            contact.setLineWrap(true);
            contact.setWrapStyleWord(true);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            mainPanel.add(contact);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton specialtiesBtn = new JButton("Animal Specialties");
            specialtiesBtn.addActionListener(e -> {
                dispose();
                new AnimalSpecialtiesScreen(clinic, location);
            });

            JButton navigateBtn = new JButton("Navigate");
            navigateBtn.addActionListener(e -> {
                try {
                    Desktop.getDesktop().browse(new URI(clinic.googleMapsLink));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            JButton backBtn = new JButton("Back");
            backBtn.addActionListener(e -> {
                dispose();
                new ClinicListScreen(location);
            });

            buttonPanel.add(specialtiesBtn);
            buttonPanel.add(navigateBtn);
            buttonPanel.add(backBtn);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            mainPanel.add(buttonPanel);

            JScrollPane scrollPane = new JScrollPane(mainPanel);
            add(scrollPane);
            setVisible(true);
        }
    }

    // ------------------ SCREEN 5: Animal Specialties ------------------
    static class AnimalSpecialtiesScreen extends JFrame {
        public AnimalSpecialtiesScreen(Clinic clinic, String location) {
            setTitle(clinic.name + " - Animal Specialties");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 500);
            setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Exit button top-left
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e -> System.exit(0));
            topPanel.add(exitButton);
            mainPanel.add(topPanel);

            JLabel title = new JLabel("Animal Specialties Handled");
            title.setFont(new Font("Arial", Font.BOLD, 20));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(title);

            JTextArea specialties = new JTextArea(
                    "Companion Mammals: Dogs, Cats\n" +
                            "Small Mammals: Rabbits, Guinea pigs, Hamsters, Gerbils, Rats & mice, Ferrets, Chinchillas, Hedgehogs\n" +
                            "Reptiles: Snakes, Lizards, Turtles & tortoises\n" +
                            "Aquatic & Invertebrates: Fish (freshwater & saltwater), Hermit crabs, Tarantulas, Scorpions\n" +
                            "Avians: Parrots, Cockatiels, Parakeets/Budgies, Lovebirds, Finches, Canaries\n" +
                            "Small Exotic: Sugar gliders, Hedgehogs\n" +
                            "Livestock: Cattle, Sheep, Goats, Pigs, Buffalo\n" +
                            "Equine: Horses, Ponies, Donkeys"
            );
            specialties.setEditable(false);
            specialties.setFont(new Font("Arial", Font.PLAIN, 14));
            specialties.setLineWrap(true);
            specialties.setWrapStyleWord(true);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            mainPanel.add(specialties);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton backBtn = new JButton("Back");
            backBtn.addActionListener(e -> {
                dispose();
                new ClinicDetailScreen(clinic, location);
            });

            JButton navigateBtn = new JButton("Navigate");
            navigateBtn.addActionListener(e -> {
                try {
                    Desktop.getDesktop().browse(new URI(clinic.googleMapsLink));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            buttonPanel.add(backBtn);
            buttonPanel.add(navigateBtn);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            mainPanel.add(buttonPanel);

            JScrollPane scrollPane = new JScrollPane(mainPanel);
            add(scrollPane);
            setVisible(true);
        }
    }

    // ------------------ CLINIC CLASS ------------------
    static class Clinic {
        String name, hours, address, googleMapsLink;

        public Clinic(String name, String hours, String address, String googleMapsLink) {
            this.name = name;
            this.hours = hours;
            this.address = address;
            this.googleMapsLink = googleMapsLink;
        }
    }
}

/*
SUMMARY:

- Added Exit button at top-left corner on all screens
- Clicking Exit closes the application from any screen
- Maintains all previous functionality:
  * Opening screen -> Location select -> Clinic catalog -> Clinic details -> Animal specialties
  * Back buttons navigate step by step
  * Navigate buttons open Google Maps for selected clinic
*/
