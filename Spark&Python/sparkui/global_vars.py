settings_dict = {'v1': None, 'namespace': None, 'base_url': None}

def get_global(variable_id):
    return settings_dict[variable_id]

def set_global(variable_id, variable_value):
    settings_dict[variable_id] = variable_value


