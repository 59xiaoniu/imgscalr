package org.imgscalr;

import java.awt.image.BufferedImage;

import junit.framework.Assert;

import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.junit.Test;

public class ScalrResizeTest extends AbstractScalrTest {
	@Test
	public void testResizeEX() {
		try {
			Scalr.resize(src, -1);
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

		try {
			Scalr.resize(src, 240, -1);
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

		try {
			Scalr.resize(src, (Method) null, 240);
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

		try {
			Scalr.resize(src, (Mode) null, 240);
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

		try {
			Scalr.resize(src, (Method) null, 240, 240);
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

		try {
			Scalr.resize(src, (Mode) null, 240, 240);
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

		try {
			Scalr.resize(src, null, null, 240);
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

		try {
			Scalr.resize(src, null, null, 240, 240);
			Assert.assertTrue(false);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testResizeSize() {
		assertEquals(load("time-square-resize-320.png"), Scalr.resize(src, 320));
	}

	@Test
	public void testResizeWH() {
		assertEquals(load("time-square-resize-640x480.png"),
				Scalr.resize(src, 640, 480));
	}

	@Test
	public void testResizeSizeSpeed() {
		assertEquals(load("time-square-resize-320-speed.png"),
				Scalr.resize(src, Method.SPEED, 320));
	}

	@Test
	public void testResizeWHSpeed() {
		assertEquals(load("time-square-resize-640x480-speed.png"),
				Scalr.resize(src, Method.SPEED, 640, 480));
	}

	@Test
	public void testResizeSizeExact() {
		assertEquals(load("time-square-resize-320-fit-exact.png"),
				Scalr.resize(src, Mode.FIT_EXACT, 320));
	}

	@Test
	public void testResizeWHExact() {
		assertEquals(load("time-square-resize-640x640-fit-exact.png"),
				Scalr.resize(src, Mode.FIT_EXACT, 640, 640));
	}

	@Test
	public void testResizeSizeSpeedExact() {
		assertEquals(load("time-square-resize-320-speed-fit-exact.png"),
				Scalr.resize(src, Method.SPEED, Mode.FIT_EXACT, 320));
	}

	@Test
	public void testResizeWHSpeedExact() {
		assertEquals(load("time-square-resize-640x640-speed-fit-exact.png"),
				Scalr.resize(src, Method.SPEED, Mode.FIT_EXACT, 640, 640));
	}

	@Test
	public void testResizeWHSpeedExactOps() {
		assertEquals(
				load("time-square-resize-640x640-speed-fit-exact-ops.png"),
				Scalr.resize(src, Method.SPEED, Mode.FIT_EXACT, 640, 640,
						Scalr.OP_GRAYSCALE));
	}

	@Test
	public void testResizeUltraQuality() {
		System.setProperty(Scalr.DEBUG_PROPERTY_NAME, "true");
		BufferedImage i = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
		Scalr.resize(i, Method.ULTRA_QUALITY, 1);

		// This test is really about having scaling to tiny sizes not looping
		// forever because of the fractional step-down calculation bottoming
		// out.
		Assert.assertTrue(true);
	}
}