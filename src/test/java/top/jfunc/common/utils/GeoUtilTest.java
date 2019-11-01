package top.jfunc.common.utils;

import org.junit.Assert;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;

/**
 * @author xiongshiyan at 2019/1/11 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class GeoUtilTest {
    @Test
    public void testPointInPoly(){
        List<Point2D.Double> points =
                Arrays.asList(
                        new Point2D.Double(116.169465,39.932670),
                        new Point2D.Double(116.160260,39.924492),
                        new Point2D.Double(116.186138,39.879817),
                        new Point2D.Double(116.150625,39.710019),
                        new Point2D.Double(116.183198,39.709920),
                        new Point2D.Double(116.226950,39.777616),
                        new Point2D.Double(116.421078,39.810771),
                        new Point2D.Double(116.442621,39.799892),
                        new Point2D.Double(116.463478,39.790066),
                        new Point2D.Double(116.588276,39.809551),
                        new Point2D.Double(116.536091,39.808859),
                        new Point2D.Double(116.573856,39.839643),
                        new Point2D.Double(116.706380,39.916740),
                        new Point2D.Double(116.657285,39.934545),
                        new Point2D.Double(116.600293,39.937770),
                        new Point2D.Double(116.540039,39.937968),
                        new Point2D.Double(116.514805,39.982375),
                        new Point2D.Double(116.499935,40.013710),
                        new Point2D.Double(116.546520,40.030443),
                        new Point2D.Double(116.687668,40.129961),
                        new Point2D.Double(116.539697,40.080659),
                        new Point2D.Double(116.503390,40.058474),
                        new Point2D.Double(116.468800,40.052578));

        Point2D.Double pointNot = new Point2D.Double(116.566298, 40.014179);
        Point2D.Double pointYes = new Point2D.Double(116.529906,39.904706);
        Point2D.Double pointYes2 = new Point2D.Double(116.367171,39.968411);

        Assert.assertFalse(GeometryUtil.isPtInPoly(pointNot, points));
        Assert.assertTrue(GeometryUtil.isPtInPoly(pointYes, points));
        Assert.assertTrue(GeometryUtil.isPtInPoly(pointYes2, points));



        Assert.assertFalse(GeometryUtil.isPointInPoly(pointNot, points));
        Assert.assertTrue(GeometryUtil.isPointInPoly(pointYes, points));
        Assert.assertTrue(GeometryUtil.isPointInPoly(pointYes2, points));


        Assert.assertFalse(GeometryUtil.isPtInPolygon(pointNot, points));
        Assert.assertTrue(GeometryUtil.isPtInPolygon(pointYes, points));
        Assert.assertTrue(GeometryUtil.isPtInPolygon(pointYes2, points));
    }
}
