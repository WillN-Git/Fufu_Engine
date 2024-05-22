#version 330

in vec2 texCoord;

uniform vec3 uBaseColor;
uniform vec3 uAmbientLight;
uniform sampler2D uSampler;

void main()
{
    vec4 finalColor = vec4(0.0);
    finalColor = texture2D(uSampler, texCoord.xy) * vec4(uBaseColor, 1.0);

    vec4 sun_light = vec4(1);

    gl_FragColor = finalColor * sun_light * vec4(uAmbientLight, 1.0);
}