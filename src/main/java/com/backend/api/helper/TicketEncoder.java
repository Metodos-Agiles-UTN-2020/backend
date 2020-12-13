package com.backend.api.helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import com.backend.api.constants.Position;


public class TicketEncoder {
  static final String CANVAS = "./src/main/resources/assets/ticket_canvas.jpg";
  static final String OUTPUT_TICKET = "./src/main/resources/assets/ticket_out.jpg";
  static final String FONT = "Tahoma";
  static final Integer FONT_SIZE = 40;

  public static String encode(String item1, String monto1, String cant1, String item2,
      String monto2, String cant2, String item3, String monto3, String cant3, String total) {
    try {
      BufferedImage back = ImageIO.read(new File(CANVAS));
      Graphics g = back.getGraphics();
      g.setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
      g.setColor(Color.BLACK);


      g.drawString(item1, Position.ITEM1_X, Position.ITEM1_Y);
      g.drawString(monto1, Position.MONTO1_X, Position.MONTO1_Y);
      g.drawString(cant1, Position.CANT1_X, Position.CANT1_Y);

      g.drawString(item2, Position.ITEM2_X, Position.ITEM2_Y);
      g.drawString(monto2, Position.MONTO2_X, Position.MONTO2_Y);
      g.drawString(cant2, Position.CANT2_X, Position.CANT2_Y);

      g.drawString(item3, Position.ITEM3_X, Position.ITEM3_Y);
      g.drawString(monto3, Position.MONTO3_X, Position.MONTO3_Y);
      g.drawString(cant3, Position.CANT3_X, Position.CANT3_Y);

      g.drawString(total, Position.TOTAL_X, Position.TOTAL_Y);

      // Guarda la imagen en el directorio /resources
      ImageIO.write(back, "jpg", new File(OUTPUT_TICKET));

      // Levanta la imagen y la convierte en base64
      String base64Image = "";
      File file = new File(OUTPUT_TICKET);
      try (FileInputStream imageInFile = new FileInputStream(file)) {
        // Reading a Image file from file system
        byte imageData[] = new byte[(int) file.length()];
        imageInFile.read(imageData);
        base64Image = Base64.getEncoder().encodeToString(imageData);
        return base64Image;

      } catch (Exception e) {
      }

    } catch (IOException e) {
    }
    return null;
  }

}
