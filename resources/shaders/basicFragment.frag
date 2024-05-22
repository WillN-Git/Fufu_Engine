#version 330

in vec2 texCoord;

uniform vec3 uColor;
uniform sampler2D uSampler;

void main()
{
    gl_FragColor = texture2D(uSampler, texCoord.xy) * vec4(uColor, 1.0);
}