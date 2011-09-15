/*
 * Copyright (c) 2011 Adobe Systems Incorporated
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of
 *  this software and associated documentation files (the "Software"), to deal in
 *  the Software without restriction, including without limitation the rights to
 *  use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 *  the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 *  FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 *  COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 *  IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.adobe.epubcheck.ops;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.adobe.epubcheck.opf.DocumentValidator;
import com.adobe.epubcheck.util.EPUBVersion;
import com.adobe.epubcheck.util.FileResourceProvider;
import com.adobe.epubcheck.util.GenericResourceProvider;
import com.adobe.epubcheck.util.Messages;
import com.adobe.epubcheck.util.URLResourceProvider;
import com.adobe.epubcheck.util.ValidationReport;

public class OPSCheckerTest {

	private String path = "com.adobe.epubcheck.test/testdocs/";

	private ValidationReport testReport;

	private DocumentValidator opsChecker;

	private GenericResourceProvider resourceProvider;

	private boolean verbose;

	/*
	 * TEST DEBUG FUNCTION
	 */
	public void testValidateDocument(String fileName, String mimeType,
			int errors, int warnings, EPUBVersion version, boolean verbose) {
		if (verbose)
			this.verbose = verbose;
		testValidateDocument(fileName, mimeType, errors, warnings, version);

	}

	public void testValidateDocument(String fileName, String mimeType,
			int errors, int warnings, EPUBVersion version) {
		testReport = new ValidationReport(fileName, String.format(
				Messages.SINGLE_FILE, mimeType, version));
		String relativePath = null;
		if (version == EPUBVersion.VERSION_2)
			relativePath = "20/single/";
		else if (version == EPUBVersion.VERSION_3)
			relativePath = "30/single/";

		if (fileName.startsWith("http://") || fileName.startsWith("https://"))
			resourceProvider = new URLResourceProvider(fileName);
		else
			resourceProvider = new FileResourceProvider(path + relativePath
					+ fileName);

		opsChecker = new OPSChecker(path + relativePath + fileName, mimeType,
				resourceProvider, testReport, version);

		opsChecker.validate();

		if (verbose) {
			verbose = false;
			System.out.println(testReport);
		}

		assertEquals(errors, testReport.getErrorCount());
		assertEquals(warnings, testReport.getWarningCount());
	}

	@Test
	public void testValidateSVGRectInvalid() {
		testValidateDocument("svg/invalid/rect.svg", "image/svg+xml", 4, 0,
				EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateSVGRectValid() {
		testValidateDocument("svg/valid/rect.svg", "image/svg+xml", 0, 0,
				EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLEdits001() {
		testValidateDocument("xhtml/valid/edits-001.xhtml",
				"application/xhtml+xml", 0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLEmbed001() {
		testValidateDocument("xhtml/valid/embed-001.xhtml",
				"application/xhtml+xml", 0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLForms001() {
		testValidateDocument("xhtml/valid/forms-001.xhtml",
				"application/xhtml+xml", 0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLGlobalAttrs001() {
		testValidateDocument("xhtml/valid/global-attrs-001.xhtml",
				"application/xhtml+xml", 0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLOps001() {
		testValidateDocument("xhtml/valid/ops-001.xhtml",
				"application/xhtml+xml", 0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLOPSMATHML001() {
		testValidateDocument("xhtml/valid/ops-mathml-001.xhtml",
				"application/xhtml+xml", 0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLOPSMATHML002() {
		testValidateDocument("xhtml/valid/ops-mathml-002.xhtml",
				"application/xhtml+xml", 0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLOPSSVG001() {
		testValidateDocument("xhtml/valid/ops-svg-001.xhtml",
				"application/xhtml+xml", 0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLRuby001() {
		testValidateDocument("xhtml/valid/ruby-001.xhtml",
				"application/xhtml+xml", 0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLSCH001() {
		testValidateDocument("xhtml/valid/sch-001.xhtml",
				"application/xhtml+xml", 0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLSections001() {
		testValidateDocument("xhtml/valid/sections-001.xhtml",
				"application/xhtml+xml", 0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLSSML() {
		testValidateDocument("xhtml/valid/ssml.xhtml", "application/xhtml+xml",
				0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLStyle001() {
		testValidateDocument("xhtml/valid/style-001.xhtml",
				"application/xhtml+xml", 0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLSwitch001() {
		testValidateDocument("xhtml/valid/switch-001.xhtml",
				"application/xhtml+xml", 0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLTables001() {
		testValidateDocument("xhtml/valid/tables-001.xhtml",
				"application/xhtml+xml", 0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLText001() {
		testValidateDocument("xhtml/valid/text-001.xhtml",
				"application/xhtml+xml", 0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLTrigger() {
		testValidateDocument("xhtml/valid/trigger.xhtml",
				"application/xhtml+xml", 0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLData() {
		testValidateDocument("xhtml/valid/data.xhtml", "application/xhtml+xml",
				0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTMLVideo() {
		testValidateDocument("xhtml/valid/video.xhtml",
				"application/xhtml+xml", 0, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTML_OPSMATHML001() {
		testValidateDocument("xhtml/invalid/ops-mathml-001.xhtml",
				"application/xhtml+xml", 4, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTML_OPSMATHML002() {
		testValidateDocument("xhtml/invalid/ops-mathml-002.xhtml",
				"application/xhtml+xml", 7, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTML_SCH001() {
		testValidateDocument("xhtml/invalid/sch-001.xhtml",
				"application/xhtml+xml", 48, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTML_Style001() {
		testValidateDocument("xhtml/invalid/style-001.xhtml",
				"application/xhtml+xml", 3, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTML_SVG001() {
		testValidateDocument("xhtml/invalid/svg-001.xhtml",
				"application/xhtml+xml", 2, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTML_Switch001() {
		testValidateDocument("xhtml/invalid/switch-001.xhtml",
				"application/xhtml+xml", 9, 0, EPUBVersion.VERSION_3);
	}

	@Test
	public void testValidateXHTML_Trigger() {
		testValidateDocument("xhtml/invalid/trigger.xhtml",
				"application/xhtml+xml", 2, 0, EPUBVersion.VERSION_3);
	}

}
