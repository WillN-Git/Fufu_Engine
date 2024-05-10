#version 330

layout (location = 0) in vec3 pos;

out vec4 color;

void main()
{
    color = vec4(clamp(pos, 0.0, 1.0), 1.0);

    gl_Position = vec4(0.25 * pos, 1.0);
}