#version 330

layout (location = 0) in vec3 pos;
layout (location = 1) in vec2 inTexCoord;

out vec4 color;
out vec2 texCoord;

uniform mat4 uTransform;

void main()
{
    color = vec4(clamp(pos, 0.0, 1.0), 1.0);

    gl_Position = uTransform * vec4(pos, 1.0);
    texCoord = inTexCoord;
}
