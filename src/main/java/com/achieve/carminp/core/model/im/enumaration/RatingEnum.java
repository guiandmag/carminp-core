package com.achieve.carminp.core.model.im.enumaration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * Enum para mapear o valor da avaliacao da frase.
 * 
 * @author guilherme.magalhaes
 * @since 12/2014
 * @version 2.0
 * 
 */
@XmlType
@XmlEnum
public enum RatingEnum {

	Star1,
	Star2,
	Star3,
	Star4,
	Star5;
	
}