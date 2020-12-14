package com.backend.api.helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.backend.api.constants.Position;



public class LicenseEncoder {

  private static final Logger logger = LogManager.getLogger(LicenseEncoder.class);

  static final String BASE_PATH = "/fotos/";

  static final String BACK = BASE_PATH + "back.jpg";
  static final String FRONT = BASE_PATH + "front.png";
  static final String OUTPUT_BACK = BASE_PATH + "back_out.png";
  static final String OUTPUT_FRONT = BASE_PATH + "front_out.png";
  static final String PROFILE = BASE_PATH + "profile.png";
  static final String FONT = "Arial";
  static final Integer FONT_SIZE = 30;

  public static String encodeFront(String dni, String apellido, String nombre, String domicilio,
      String fecha_nac, String otorgamiento, String clase, String vencimiento, String foto) {
    try {
      apellido = apellido.toUpperCase();
      nombre = nombre.toUpperCase();
      domicilio = domicilio.toUpperCase();
      fecha_nac = fecha_nac.toUpperCase();
      otorgamiento = otorgamiento.toUpperCase();
      clase = clase.toUpperCase();
      vencimiento = vencimiento.toUpperCase();

      BufferedImage back = ImageIO.read(new File(FRONT));
      logger.debug(back);
      Graphics licence = back.getGraphics();
      logger.debug(licence);


      licence.setFont(new Font(FONT, Font.BOLD, 50));
      licence.setColor(Color.BLACK);

      byte[] imageByte;
      imageByte = Base64.getDecoder().decode(foto);
      ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
      BufferedImage image = ImageIO.read(bis);
      ImageIO.write(image, "png", new File(PROFILE));
      bis.close();

      BufferedImage prof = ImageIO.read(new File(PROFILE));

      // DNI
      licence.drawString(dni, Position.DNI_X, Position.DNI_Y);
      // Apellido
      licence.drawString(apellido, Position.APELLIDO_X, Position.APELLIDO_Y);
      // Nombres
      licence.drawString(nombre, Position.NOMBRES_X, Position.NOMBRES_Y);
      // Domiclio
      licence.drawString(domicilio, Position.DOMICILIO_X, Position.DOMICILIO_Y);
      // Fecha Nac
      licence.drawString(fecha_nac, Position.FECHA_NAC_X, Position.FECHA_NAC_Y);
      // Otorgamiento
      licence.drawString(otorgamiento, Position.OTORGAMIENTO_X, Position.OTORGAMIENTO_Y);
      // Clase
      licence.drawString(clase, Position.CLASE_X, Position.CLASE_Y);
      // Vencimiento
      licence.drawString(vencimiento, Position.VENCIMIENTO_X, Position.VENCIMIENTO_Y);
      // Foto de perfil
      licence.drawImage(prof, 64, 270, null);


      ImageIO.write(back, "png", new File(OUTPUT_FRONT));
      String base64Image = "";
      File file = new File(OUTPUT_FRONT);
      try (FileInputStream imageInFile = new FileInputStream(file)) {
        byte imageData[] = new byte[(int) file.length()];
        imageInFile.read(imageData);
        base64Image = Base64.getEncoder().encodeToString(imageData);
        return "data:image/png;base64," + base64Image;

      } catch (Exception e) {
        logger.debug(e);
      }

    } catch (IOException e) {
      logger.debug(e);
    }
    return null;
  }

  public static String encodeBack(String observaciones, String donante, String grupo_factor,
      String limitaciones) {

    try {
      Integer FONT_SIZE = 22;
      BufferedImage back = ImageIO.read(new File(BACK));
      logger.debug(back);
      Graphics licence = back.getGraphics();
      logger.debug(licence);
      licence.setFont(new Font(FONT, Font.BOLD, FONT_SIZE));
      licence.setColor(Color.BLACK);

      donante = donante.toUpperCase();
      grupo_factor = grupo_factor.toUpperCase();
      observaciones = observaciones.toUpperCase();


      // Donante
      licence.drawString(donante, Position.DONANTE_X, Position.DONANTE_Y);

      // Grupo y Factor
      licence.drawString(grupo_factor, Position.GRUPO_X, Position.GRUPO_Y);

      // Observaciones
      licence.drawString(observaciones, Position.OBSERVACIONES_X, Position.OBSERVACIONES_Y);

      // Limitaciones
      if (limitaciones.length() > 70) {
        licence.drawString(limitaciones.substring(0, 50), Position.LIMITACIONES_X,
            Position.LIMITACIONES_Y);
        licence.drawString(limitaciones.substring(51), Position.LIMITACIONES_X2,
            Position.LIMITACIONES_Y2);
      } else {
        licence.drawString(limitaciones, Position.LIMITACIONES_X, Position.LIMITACIONES_Y);
      }


      ImageIO.write(back, "png", new File(OUTPUT_BACK));
      String base64Image = "";
      File file = new File(OUTPUT_BACK);
      try (FileInputStream imageInFile = new FileInputStream(file)) {
        byte imageData[] = new byte[(int) file.length()];
        imageInFile.read(imageData);
        base64Image = Base64.getEncoder().encodeToString(imageData);
        return "data:image/png;base64," + base64Image;
      } catch (Exception e) {
        logger.debug(e);

      }

    } catch (IOException e) {
      logger.debug(e);
    }
    return null;
  }
}
