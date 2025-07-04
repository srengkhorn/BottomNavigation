package com.example.bottombars.liquidglass

import org.intellij.lang.annotations.Language

internal object LiquidGlassShaders {

    @Language("AGSL")
    private val colorShaderUtils = """// This file belongs to Kyant. You must not use it without permission.
    const half3 rgbToY = half3(0.2126, 0.7152, 0.0722);
    
    float luma(half4 color) {
        return dot(toLinearSrgb(color.rgb), rgbToY);
    }
        """.trimIndent()

    @Language("AGSL")
    private val refractionShaderUtils = """// This file belongs to Kyant. You must not use it without permission.
    float circleMap(float x) {
        return 1.0 - sqrt(1.0 - x * x);
    }
    
    float sdRectangle(float2 coord, float2 halfSize) {
        float2 d = abs(coord) - halfSize;
        float outside = length(max(d, 0.0));
        float inside = min(max(d.x, d.y), 0.0);
        return outside + inside;
    }
    
    float sdRoundedRectangle(float2 coord, float2 halfSize, float cornerRadius) {
        float2 innerHalfSize = halfSize - float2(cornerRadius);
        return sdRectangle(coord, innerHalfSize) - cornerRadius;
    }
    
    float2 gradSdRoundedRectangle(float2 coord, float2 halfSize, float cornerRadius) {
        float2 innerHalfSize = halfSize - float2(cornerRadius);
        float2 cornerCoord = abs(coord) - innerHalfSize;
        
        if (cornerCoord.x >= 0.0 && cornerCoord.y >= 0.0) {
            return sign(coord) * normalize(cornerCoord);
        } else {
            return sign(coord) * ((-cornerCoord.x < -cornerCoord.y) ? float2(1.0, 0.0) : float2(0.0, 1.0));
        }
    }
    
    half4 refractionColor(float2 coord, float2 size, float cornerRadius, float eccentricFactor, float height, float amount) {
        float2 halfSize = size * 0.5;
        float2 centeredCoord = coord - halfSize;
        float sd = sdRoundedRectangle(centeredCoord, halfSize, cornerRadius);
        sd = min(sd, 0.0);
        
        if (sd <= 0.0 && -sd <= height) {
            float maxGradRadius = max(min(halfSize.x, halfSize.y), cornerRadius);
            float gradRadius = min(cornerRadius * 1.5, maxGradRadius);
            float2 normal = gradSdRoundedRectangle(centeredCoord, halfSize, gradRadius);
            
            float refractedDistance = circleMap(1.0 - -sd / height) * amount;
            float2 refractedDirection = normalize(normal + eccentricFactor * normalize(centeredCoord));
            float2 refractedCoord = coord + refractedDistance * refractedDirection;
            if (refractedCoord.x < 0.0 || refractedCoord.x >= size.x ||
                refractedCoord.y < 0.0 || refractedCoord.y >= size.y) {
                return half4(0.0, 0.0, 0.0, 1.0);
            }
            
            return image.eval(refractedCoord);
        } else {
            return image.eval(coord);
        }
    }
        """.trimIndent()

    @Language("AGSL")
    val refractionShaderString = """// This file belongs to Kyant. You must not use it without permission.
    uniform shader image;
    
    uniform float2 size;
    uniform float cornerRadius;
    
    uniform float refractionHeight;
    uniform float refractionAmount;
    uniform float eccentricFactor;
    
    uniform float bleedOpacity;
    
    $colorShaderUtils
    $refractionShaderUtils
    
    half4 main(float2 coord) {
        half4 color = refractionColor(coord, size, cornerRadius, eccentricFactor, refractionHeight, refractionAmount);
        if (bleedOpacity <= 0.0) {
            return color;
        } else {
            float luma = luma(color);
            color *= 1.0 - bleedOpacity * luma;
            return color;
        }
    }"""

    @Language("AGSL")
    val bleedShaderString = """// This file belongs to Kyant. You must not use it without permission.
    uniform shader image;
    
    uniform float2 size;
    uniform float cornerRadius;
    
    uniform float eccentricFactor;
    uniform float bleedAmount;
    
    $colorShaderUtils
    $refractionShaderUtils
    
    half4 main(float2 coord) {
        half4 color = refractionColor(coord, size, cornerRadius, eccentricFactor, cornerRadius * 3.5, bleedAmount);
        float luma = luma(color);
        color.rgb = mix(color.rgb, half3(1.0), 0.5 * circleMap(1.0 - luma));
        return color;
    }"""

    @Language("AGSL")
    val colorManipulationShaderString = """// This file belongs to Kyant. You must not use it without permission.
    uniform shader image;
    
    uniform float contrast;
    uniform float whitePoint;
    uniform float chromaMultiplier;
    
    $colorShaderUtils
    
    half4 saturateColor(half4 color, float amount) {
        half3 linearSrgb = toLinearSrgb(color.rgb);
        float y = dot(linearSrgb, rgbToY);
        half3 gray = half3(y);
        half3 adjustedLinearSrgb = mix(gray, linearSrgb, amount);
        half3 adjustedSrgb = fromLinearSrgb(adjustedLinearSrgb);
        return half4(adjustedSrgb, color.a);
    }
    
    half4 main(float2 coord) {
        half4 color = image.eval(coord);
        
        color = saturateColor(color, chromaMultiplier);
        
        float3 target = (whitePoint > 0.0) ? float3(1.0) : float3(0.0);
        color.rgb = mix(color.rgb, target, abs(whitePoint));
        
        color.rgb = (color.rgb - 0.5) * (1.0 + contrast) + 0.5;
        
        return color;
    }"""
}
