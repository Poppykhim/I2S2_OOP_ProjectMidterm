package ui.MenuGroup;

// First, create a new PrintPreviewDialog class
// First, create a new PrintPreviewDialog class
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import javax.swing.*;

public class PrintPreviewDialog extends JDialog {

    private JPanel contentPanel;
    private PageFormat pageFormat;
    private Printable printable;
    private double scale = 2; // Default zoom level

    public PrintPreviewDialog(Frame parent, Printable printable, PageFormat pageFormat) {
        super(parent, "Print Preview", true);
        this.printable = printable;
        this.pageFormat = pageFormat;

        initComponents();
        setSize(800, 600);
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Create preview panel
        JPanel previewPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintPreview(g);
            }
        };
        previewPanel.setBackground(Color.GRAY);
        previewPanel.setPreferredSize(new Dimension(600, 800));

        JScrollPane scrollPane = new JScrollPane(previewPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Create toolbar
        JPanel toolbar = new JPanel(new FlowLayout());

        JButton zoomInBtn = new JButton("Zoom In");
        zoomInBtn.addActionListener(e -> {
            scale += 0.1;
            repaint();
        });

        JButton zoomOutBtn = new JButton("Zoom Out");
        zoomOutBtn.addActionListener(e -> {
            if (scale > 0.2) {
                scale -= 0.1;
                repaint();
            }
        });

        JButton printBtn = new JButton("Print");
        printBtn.addActionListener(e -> {
            dispose();
            actuallyPrint();
        });

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> dispose());

        toolbar.add(zoomOutBtn);
        toolbar.add(new JLabel(String.format("%.0f%%", scale * 100)));
        toolbar.add(zoomInBtn);
        toolbar.add(new JSeparator(SwingConstants.VERTICAL));
        toolbar.add(printBtn);
        toolbar.add(cancelBtn);

        add(toolbar, BorderLayout.NORTH);
    }

    private void paintPreview(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Set rendering hints for better quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Calculate page size and position
        double pageWidth = pageFormat.getWidth() * scale;
        double pageHeight = pageFormat.getHeight() * scale;

        int x = (getWidth() - (int) pageWidth) / 2;
        int y = 50; // Top margin

        // Draw page background (white paper)
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, (int) pageWidth, (int) pageHeight);

        // Draw page border
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, (int) pageWidth, (int) pageHeight);

        // Draw drop shadow
        g2d.setColor(new Color(0, 0, 0, 50));
        g2d.fillRect(x + 5, y + 5, (int) pageWidth, (int) pageHeight);

        // Clip to page area and draw content
        g2d.clipRect(x, y, (int) pageWidth, (int) pageHeight);
        g2d.translate(x, y);
        g2d.scale(scale, scale);

        try {
            printable.print(g2d, pageFormat, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        g2d.dispose();
    }

    private void actuallyPrint() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setJobName("Receipt Print");
        printerJob.setPrintable(printable, pageFormat);

        if (printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Printing failed: " + e.getMessage(),
                        "Print Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}// Now modify your MenuRight class - replace the printToPrinter() method with this:

